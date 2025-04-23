package com.joblog.controllers.impl;

import com.joblog.controllers.interfaces.IAuthController;
import com.joblog.exceptions.UnAuthorizedException;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.request.AuthRequest;
import com.joblog.repositories.interfaces.IUserRepository;
import com.joblog.services.interfaces.IAuthService;
import com.joblog.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {

  @Autowired private IUserRepository userRepo;
  @Autowired private JwtUtil jwtUtil;
  @Autowired private IAuthService authService;

  @Override
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
    authService.registerUser(
        authRequest.getEmailId(), authRequest.getPassword(), authRequest.getUserName());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<?> login(AuthRequest authRequest) {
    try {
      authService.loginUser(authRequest.getEmailId(), authRequest.getPassword());
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (UnAuthorizedException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
