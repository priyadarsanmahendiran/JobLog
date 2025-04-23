package com.joblog.services.interfaces;

import com.joblog.models.response.AuthResponse;

public interface IAuthService {

  void registerUser(String emailId, String password, String name);

  AuthResponse loginUser(String emailId, String password);
}
