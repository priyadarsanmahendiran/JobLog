package com.joblog.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.joblog.TestBase;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.entities.Users;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import com.joblog.repositories.interfaces.IUserRepository;
import com.joblog.repositories.interfaces.IWorkLogRepository;
import com.joblog.utils.Utils;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class LogServiceTest extends TestBase {

  @Mock private IWorkLogRepository workLogRepository;

  @Mock private IUserRepository userRepository;

  @Mock private Utils utils;

  @InjectMocks private LogService logService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void addLogs() {
    LogRequest logRequest = prepareLogRequest();
    Users user = prepareUser();
    Worklog log = prepareWorkLog();

    Mockito.when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
    Mockito.when(utils.transformRequestToWorklog(any(LogRequest.class), any(Users.class)))
        .thenReturn(log);

    logService.addLogs(logRequest);

    Mockito.verify(workLogRepository, Mockito.times(1)).save(log);
  }

  @Test
  void addLogsThrowsException() {
    LogRequest logRequest = prepareLogRequest();

    Mockito.when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
    Mockito.when(utils.transformRequestToWorklog(any(LogRequest.class), any(Users.class)))
        .thenReturn(prepareWorkLog());

    Assertions.assertThrows(UserNotFoundException.class, () -> logService.addLogs(logRequest));
  }

  @Test
  void fetchLogsByUser() {
    UUID userId = UUID.randomUUID();
    Worklog worklog = prepareWorkLog();
    Mockito.when(workLogRepository.findByUserId(userId))
        .thenReturn(Optional.of(Collections.singletonList(worklog)));
    Mockito.when(utils.transformWorkLogToResponse(any())).thenReturn(getLogResponse());

    var response = logService.fetchLogsByUser(userId);

    Assertions.assertNotNull(response);
  }

  @Test
  void fetchLogsByUserEmptyUser() {
    UUID userId = UUID.randomUUID();
    Mockito.when(workLogRepository.findByUserId(userId)).thenReturn(Optional.empty());
    var response = logService.fetchLogsByUser(userId);
    Assertions.assertEquals(0, response.size());
  }
}
