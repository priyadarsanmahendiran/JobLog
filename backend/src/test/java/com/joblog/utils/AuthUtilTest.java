package com.joblog.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.joblog.TestBase;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthUtilTest extends TestBase {

  @Mock private JwtUtil jwtUtil;

  @InjectMocks private AuthUtil authUtil;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getUserIdFromHeader_ValidToken() {
    String authHeader = "Bearer validToken";
    UUID expectedUserId = UUID.randomUUID();

    when(jwtUtil.extractUserId("validToken")).thenReturn(expectedUserId);

    UUID actualUserId = authUtil.getUserIdFromHeader(authHeader);

    assertNotNull(actualUserId);
    assertEquals(expectedUserId, actualUserId);
    verify(jwtUtil, times(1)).extractUserId("validToken");
  }

  @Test
  void getUserIdFromHeader_InvalidHeader() {
    String authHeader = "Inv";

    assertThrows(
        StringIndexOutOfBoundsException.class, () -> authUtil.getUserIdFromHeader(authHeader));
    verify(jwtUtil, never()).extractUserId(anyString());
  }
}
