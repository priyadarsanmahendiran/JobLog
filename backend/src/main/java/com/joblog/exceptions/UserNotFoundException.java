package com.joblog.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(UUID userId) {
    super("User with ID " + userId + " not found.");
  }

  public UserNotFoundException(String field, String value) {
    super("User with " + field + " '" + value + "' not found.");
  }
}
