package com.joblog.utils;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

  private final JwtUtil jwtUtil;

  public AuthUtil(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  public UUID getUserIdFromHeader(String authHeader) {
    String token = authHeader.substring(7); // strip "Bearer "
    return jwtUtil.extractUserId(token);
  }
}
