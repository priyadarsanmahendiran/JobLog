package com.joblog.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.joblog.TestBase;
import io.jsonwebtoken.MalformedJwtException;
import java.lang.reflect.Field;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class JwtUtilTest extends TestBase {

  @InjectMocks private JwtUtil jwtUtil;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    Field secretField = JwtUtil.class.getDeclaredField("secretString");
    secretField.setAccessible(true);
    secretField.set(jwtUtil, "testsecretkeytestsecretkey1234567890");
    jwtUtil.init();
  }

  @Test
  void generateToken_Success() {
    UUID userId = UUID.randomUUID();
    String token = jwtUtil.generateToken(userId);

    assertNotNull(token);
    assertTrue(token.length() > 0);
  }

  @Test
  void extractUserId_ValidToken() {
    UUID userId = UUID.randomUUID();
    String token = jwtUtil.generateToken(userId);

    UUID extractedUserId = jwtUtil.extractUserId(token);

    assertNotNull(extractedUserId);
    assertEquals(userId, extractedUserId);
  }

  @Test
  void extractUserId_InvalidToken() {
    String invalidToken = "invalidToken";

    assertThrows(MalformedJwtException.class, () -> jwtUtil.extractUserId(invalidToken));
  }

  @Test
  void isTokenValid_ValidToken() {
    UUID userId = UUID.randomUUID();
    String token = jwtUtil.generateToken(userId);

    assertTrue(jwtUtil.isTokenValid(token));
  }

  @Test
  void isTokenValid_InvalidToken() {
    String invalidToken = "invalidToken";

    assertFalse(jwtUtil.isTokenValid(invalidToken));
  }
}
