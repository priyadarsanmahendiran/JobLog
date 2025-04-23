package com.joblog.controllers.interfaces;

import com.joblog.models.request.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/auth")
public interface IAuthController {

  ResponseEntity<?> registerUser(AuthRequest authRequest);

  ResponseEntity<?> login(AuthRequest authRequest);
}
