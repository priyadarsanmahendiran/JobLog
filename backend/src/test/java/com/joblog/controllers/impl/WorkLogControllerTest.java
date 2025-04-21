package com.joblog.controllers.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.joblog.TestBase;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.request.LogRequest;
import com.joblog.services.interfaces.ILogService;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class WorkLogControllerTest extends TestBase {

  @Mock private ILogService logService;

  @InjectMocks private WorkLogController workLogController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void addLogs() {
    LogRequest logRequest = prepareLogRequest();
    doNothing().when(logService).addLogs(any(LogRequest.class));

    var response = workLogController.addLogs(logRequest);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  void addLogsBadRequest() {
    LogRequest logRequest = prepareLogRequest();
    doThrow(UserNotFoundException.class).when(logService).addLogs(any(LogRequest.class));

    var response = workLogController.addLogs(logRequest);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void getLogsForUser() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    when(logService.fetchLogsByUser(any())).thenReturn(getLogResponse());
    var response = workLogController.getLogsForUser(userId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getLogsForUserEmptyResponse() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    when(logService.fetchLogsByUser(any())).thenReturn(Collections.emptyList());
    var response = workLogController.getLogsForUser(userId);

    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
}
