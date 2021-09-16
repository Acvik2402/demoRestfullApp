package com.example.demo.users.service;

import com.example.demo.users.util.Status;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckingScheduler {

  private final UserRepository userRepository;

  @Scheduled(fixedRate = 1000)
  private void checkUserTimeInfo() {
    List<UserEntity> userEntities = userRepository.findAllByCurrentStatus(Status.ONLINE);
    userEntities.forEach(userEntity -> {
      if (Duration.between(userEntity.getLastOnlineStatusTime(), Instant.now()).toMinutes() >= 5 &&
          userEntity.getCurrentStatus().equals(Status.ONLINE)) {
        userEntity.setCurrentStatus(Status.AWAY);
        userRepository.save(userEntity);
      }
    });
  }
}
