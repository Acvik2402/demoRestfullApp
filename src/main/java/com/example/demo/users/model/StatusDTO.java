package com.example.demo.users.model;

import com.example.demo.users.util.Status;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@AllArgsConstructor
public class StatusDTO {
  UUID userid;
  Status newStatus;
  Status currentStatus;
}
