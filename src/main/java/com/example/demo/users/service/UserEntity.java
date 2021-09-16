package com.example.demo.users.service;

import com.example.demo.users.util.Status;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "user_table")
public class UserEntity {
  @Id
  @Column(name = "user_id", columnDefinition = "BINARY(16)")
  @GeneratedValue(generator = "custom-uuid")
  @GenericGenerator(
      name = "custom-uuid",
      strategy = "org.hibernate.id.UUIDGenerator",
      parameters = {
          @Parameter(
              name = "uuid_gen_strategy_class",
              value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
          )
      }
  )
  private UUID id;
  @Column(name = "user_name")
  private String userName;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @Enumerated(EnumType.STRING)
  @Column(name = "current_status")
  private Status currentStatus;
  @Column(name = "last_online_status_time")
  private Instant lastOnlineStatusTime;
}
