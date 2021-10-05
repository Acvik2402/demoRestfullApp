package com.example.demo.users.service;

import com.example.demo.users.model.StatusDTO;
import com.example.demo.users.model.UserDTO;
import com.example.demo.users.util.Status;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Transactional
  public ResponseEntity<Object> save(UserDTO userDTO) {
    String securePassword = DigestUtils.md5Hex(userDTO.getPassword());
    userDTO.setPassword(securePassword);
    UserEntity entity = userMapper.convert(userDTO);
    entity.setLastOnlineStatusTime(Instant.now());
    entity.setCurrentStatus(Status.ONLINE);
    if (userRepository.existsByEmail(entity.getEmail())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exist");
    }
    return ResponseEntity.ok(userMapper.convert(userRepository.save(entity)).getId());
  }

  public ResponseEntity<Object> getById(UUID userId) {
    if (userRepository.existsById(userId)) {
      return ResponseEntity.ok(userRepository.findById(userId).map(userMapper::convert));
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
  }

  @Transactional
  public ResponseEntity<Object> updateUserStatus(UUID userId, String status) {
    if (!(userRepository.existsById(userId))) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
    }
    if (!(EnumUtils.isValidEnumIgnoreCase(Status.class, status))) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid status");
    }
    UserEntity userEntity = userRepository.getById(userId);
    Status lastStatus = userEntity.getCurrentStatus();
    userEntity.setCurrentStatus(Status.valueOf(status));
    if (status.equalsIgnoreCase(Status.ONLINE.name())) {
      userEntity.setLastOnlineStatusTime(Instant.now());
    }
    userRepository.save(userEntity);
    return ResponseEntity.ok(new StatusDTO(userId, Status.valueOf(status), lastStatus));
  }
}
