package com.joblog.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.joblog.TestBase;
import com.joblog.exceptions.UnAuthorizedException;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.entities.Users;
import com.joblog.models.response.AuthResponse;
import com.joblog.repositories.interfaces.IUserRepository;
import com.joblog.utils.JwtUtil;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthServiceTest extends TestBase {

  @Mock private IUserRepository userRepository;

  @Mock private PasswordEncoder passwordEncoder;

  @Mock private JwtUtil jwtUtil;

  @InjectMocks private AuthService authService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void registerUser_Success() {

    when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

    authService.registerUser("test@example.com", "password", "TestUser");

    verify(userRepository, times(1)).save(any(Users.class));
  }

  @Test
  void loginUser_Success() {
    Users user = prepareUser();

    when(userRepository.findByEmailId("test@example.com")).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
    when(jwtUtil.generateToken(user.getId())).thenReturn("jwtToken");

    AuthResponse response = authService.loginUser("test@example.com", "password");

    assertNotNull(response);
    assertEquals("jwtToken", response.getToken());
  }

  @Test
  void loginUser_UserNotFound() {
    when(userRepository.findByEmailId("test@example.com")).thenReturn(Optional.empty());

    assertThrows(
        UserNotFoundException.class, () -> authService.loginUser("test@example.com", "password"));
  }

  @Test
  void loginUser_UnAuthorized() {
    Users user = prepareUser();

    when(userRepository.findByEmailId("test@example.com")).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(false);

    assertThrows(
        UnAuthorizedException.class, () -> authService.loginUser("test@example.com", "password"));
  }
}
