package com.example.demo.users.service;

import com.example.demo.users.model.UserDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class UserInfoController {

  private final UserService userService;

  @PostMapping("/add-user")
  public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO) {
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

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}
