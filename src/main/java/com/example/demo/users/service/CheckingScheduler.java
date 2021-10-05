package com.example.demo.users.service;

import com.example.demo.users.util.Status;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CheckingScheduler {

  private final UserRepository userRepository;

  @Transactional
  @Scheduled(fixedRate = 1000)
  public void checkUserTimeInfo() {
    List<UserEntity> userEntities = userRepository.findAllByCurrentStatus(Status.ONLINE);
    userEntities.forEach(userEntity -> {
      if (Duration.between(userEntity.getLastOnlineStatusTime(), Instant.now()).toMinutes() >= 5 ) {
        userEntity.setCurrentStatus(Status.AWAY);
        userRepository.save(userEntity);
      }
    });
  }
}
