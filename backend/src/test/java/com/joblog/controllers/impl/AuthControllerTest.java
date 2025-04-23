package com.joblog.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.joblog.TestBase;
import com.joblog.exceptions.UnAuthorizedException;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.request.AuthRequest;
import com.joblog.services.interfaces.IAuthService;
import com.joblog.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AuthControllerTest extends TestBase {

  @Mock private IAuthService authService;

  @Mock private JwtUtil jwtUtil;

  @InjectMocks private AuthController authController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void registerUser_Success() {
    AuthRequest authRequest = new AuthRequest("test@example.com", "password", "TestUser");

    doNothing().when(authService).registerUser(anyString(), anyString(), anyString());

    ResponseEntity<?> response = authController.registerUser(authRequest);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(authService, times(1))
        .registerUser(
            authRequest.getEmailId(), authRequest.getPassword(), authRequest.getUserName());
  }

  @Test
  void login_Success() {
    AuthRequest authRequest = new AuthRequest("test@example.com", "password", "user1");

    ResponseEntity<?> response = authController.login(authRequest);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(authService, times(1)).loginUser(authRequest.getEmailId(), authRequest.getPassword());
  }

  @Test
  void login_UserNotFound() {
    AuthRequest authRequest = new AuthRequest("test@example.com", "password", "user1");

    doThrow(UserNotFoundException.class).when(authService).loginUser(anyString(), anyString());

    ResponseEntity<?> response = authController.login(authRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    verify(authService, times(1)).loginUser(authRequest.getEmailId(), authRequest.getPassword());
  }

  @Test
  void login_UnAuthorized() {
    AuthRequest authRequest = new AuthRequest("test@example.com", "password", "user1");

    doThrow(UnAuthorizedException.class).when(authService).loginUser(anyString(), anyString());

    ResponseEntity<?> response = authController.login(authRequest);

    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    verify(authService, times(1)).loginUser(authRequest.getEmailId(), authRequest.getPassword());
  }
}
