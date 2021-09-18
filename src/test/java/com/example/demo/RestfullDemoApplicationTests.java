package com.example.demo;

import com.example.demo.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@WebMvcTest()
class RestfullDemoApplicationTests {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void testIncorrectDTOThrowsBadRequestByValidation() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/add-user")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"user@server.com\"}"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void testCorrectDTOThrowsBadRequestByValidation() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/add-user")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"userName\":\"Somename\","
            + "\"email\":\"user@server.com\","
            + "\"phoneNumber\":\"+79997771177\","
            + "\"password\":\"12345aaaa\"" +
            "}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
