package com.joblog.utils;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

  @Autowired private JwtUtil jwtUtil;

  public UUID getUserIdFromHeader(String authHeader) {
    String token = authHeader.substring(7); // strip "Bearer "
    return jwtUtil.extractUserId(token);
  }
}
