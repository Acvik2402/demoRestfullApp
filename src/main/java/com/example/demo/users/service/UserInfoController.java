package com.example.demo.users.service;

import com.example.demo.users.model.UserDTO;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class UserInfoController {

  private final UserService userService;

  @PostMapping("/add-user")
  ResponseEntity<Object> saveUser(@Valid @RequestBody UserDTO userDTO) {
    return userService.save(userDTO);
  }

  @GetMapping("/find")
  public ResponseEntity<Object> findUserByID(@RequestParam("id") UUID id) {
    return userService.getById(id);
  }

  @GetMapping("/update")
  public ResponseEntity<Object> updateUserStatus(@RequestParam("id") UUID id, @RequestParam("status") String status) {
    return userService.updateUserStatus(id, status);
  }

}
