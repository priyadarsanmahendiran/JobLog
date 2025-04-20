package com.joblog.controllers.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import com.joblog.models.request.LogRequest;
import com.joblog.services.interfaces.ILogService;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class WorkLogControllerTest {

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

  private LogRequest prepareLogRequest() {
    return LogRequest.builder()
        .userId(UUID.randomUUID())
        .logDate(new Date())
        .blockers(Collections.singletonList("BLOCKER1"))
        .tasksDone(Collections.singletonList("TASK1"))
        .tasksPlanned(Collections.singletonList("TASK2"))
        .build();
  }
}
