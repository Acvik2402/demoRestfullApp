package com.example.demo.users.service;

import com.example.demo.users.model.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDTO convert(UserEntity userEntity) {
    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(userEntity, userDTO);
    return userDTO;
  }

  public UserEntity convert(UserDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(userDTO, userEntity);
    return userEntity;
  }
}
