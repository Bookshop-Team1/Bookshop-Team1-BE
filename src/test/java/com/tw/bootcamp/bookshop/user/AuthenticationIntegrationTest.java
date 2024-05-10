package com.tw.bootcamp.bookshop.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.bootcamp.bookshop.user.dto.JwtRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private AuthenticationManager authenticationManager;

  @MockBean private UserService userService;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    UserDetails userDetails = Mockito.mock(UserDetails.class);
    Mockito.when(userService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);
  }

  @Test
  public void givenValidCredentials_whenAuthenticate_thenSuccess() throws Exception {
    JwtRequest request = new JwtRequest("username", "password");
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  @Test
  public void givenInvalidCredentials_whenAuthenticate_thenUnauthorized() throws Exception {
    JwtRequest request = new JwtRequest("invalidUsername", "invalidPassword");
    when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/users/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isUnauthorized());
  }
}
