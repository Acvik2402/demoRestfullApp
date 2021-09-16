package com.example.demo.users.model;

import static com.example.demo.users.util.Constant.RFC_5322_REGEXP;

import com.example.demo.users.util.Status;
import java.util.UUID;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class UserDTO {
  @Id
  private UUID id;
  @NotEmpty(message = "user name mustn't be blank")
  private String userName;
  @NotEmpty(message = "email mustn't be blank")
  @Email(regexp = RFC_5322_REGEXP, message = "The email does not correspond RFC 5322")
  private String email;
  @NotEmpty(message = "phone number mustn't be blank")
  private String phoneNumber;
  @Size(min = 5, max = 15, message = "password mustn't be between 5 and 15 size")
  private String password;
  private Status currentStatus;
}
