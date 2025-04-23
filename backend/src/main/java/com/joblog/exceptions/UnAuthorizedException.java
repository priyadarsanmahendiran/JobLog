package com.joblog.exceptions;

public class UnAuthorizedException extends RuntimeException {

  public UnAuthorizedException() {
    super("User is not authorized");
  }

  public UnAuthorizedException(String message) {
    super(message);
  }
}
