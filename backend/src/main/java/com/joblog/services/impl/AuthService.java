package com.joblog.services.impl;

import com.joblog.exceptions.UnAuthorizedException;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.entities.Users;
import com.joblog.models.response.AuthResponse;
import com.joblog.repositories.interfaces.IUserRepository;
import com.joblog.services.interfaces.IAuthService;
import com.joblog.utils.JwtUtil;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

  @Autowired private IUserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private JwtUtil jwtUtil;

  @Override
  public void registerUser(String emailId, String password, String name) {
    Users users = new Users();
    users.setId(UUID.randomUUID());
    users.setEmailId(emailId);
    users.setPassword(passwordEncoder.encode(password));
    users.setName(name);
    userRepository.save(users);
  }

  @Override
  public AuthResponse loginUser(String emailId, String password) {
    Optional<Users> user = userRepository.findByEmailId(emailId);
    if (user.isEmpty()) {
      throw new UserNotFoundException("Email", emailId);
    }

    if (!passwordEncoder.matches(password, user.get().getPassword())) {
      throw new UnAuthorizedException();
    }

    String jwtToken = jwtUtil.generateToken(user.get().getId());
    AuthResponse authResponse = new AuthResponse();
    authResponse.setToken(jwtToken);
    return authResponse;
  }
}
