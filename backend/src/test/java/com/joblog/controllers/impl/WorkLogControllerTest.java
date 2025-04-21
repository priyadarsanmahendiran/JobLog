package com.joblog.controllers.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import com.joblog.TestBase;
import com.joblog.models.request.LogRequest;
import com.joblog.services.interfaces.ILogService;
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
}
