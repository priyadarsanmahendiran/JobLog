package com.joblog.controllers.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.joblog.TestBase;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import com.joblog.services.interfaces.ILogService;
import com.joblog.utils.AuthUtil;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class WorkLogControllerTest extends TestBase {

  @Mock private ILogService logService;

  @Mock private AuthUtil authUtil;

  @InjectMocks private WorkLogController workLogController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void addLogs() {
    LogRequest logRequest = prepareLogRequest();
    doNothing().when(logService).addLogs(any(LogRequest.class), any());
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());

    var response = workLogController.addLogs("DUMMY", logRequest);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  void addLogsBadRequest() {
    LogRequest logRequest = prepareLogRequest();
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());
    doThrow(UserNotFoundException.class).when(logService).addLogs(any(LogRequest.class), any());

    var response = workLogController.addLogs("DUMMY", logRequest);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void getLogsForUser() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    when(logService.fetchLogsByUser(any())).thenReturn(getLogResponse());
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());
    var response = workLogController.getLogsForUser(userId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void getLogsForUserEmptyResponse() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    when(logService.fetchLogsByUser(any())).thenReturn(Collections.emptyList());
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());
    var response = workLogController.getLogsForUser(userId);

    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void getLogsForUserBetweenDates() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    when(logService.fetchLogsByUserIdBetweenDates(any(), any(), any()))
        .thenReturn(getLogResponse());
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());
    var response =
        workLogController.getLogsForUserAndTimeRange(userId, LocalDate.now(), LocalDate.now());

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
  }

  @Test
  void getLogsForUserBetweenDatesEmptyResponse() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    when(logService.fetchLogsByUserIdBetweenDates(any(), any(), any()))
        .thenReturn(Collections.emptyList());
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());
    var response =
        workLogController.getLogsForUserAndTimeRange(userId, LocalDate.now(), LocalDate.now());

    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
    Assertions.assertTrue(response.getBody().isEmpty());
  }

  @Test
  void getLogsForUserAndDate() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    LogResponse logResponse = getLogResponse().get(0);
    when(logService.fetchLogsByUserIdAndDate(any(), any())).thenReturn(logResponse);
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());
    var response = workLogController.getLogsForUserAndDate(userId, LocalDate.now());

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
  }

  @Test
  void getLogsForUserAndDateEmptyResponse() {
    String userId = "123e4567-e89b-12d3-a456-426614174000";
    when(logService.fetchLogsByUserIdAndDate(any(), any())).thenReturn(null);
    when(authUtil.getUserIdFromHeader(any())).thenReturn(UUID.randomUUID());
    var response = workLogController.getLogsForUserAndDate(userId, LocalDate.now());

    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    Assertions.assertNull(response.getBody());
  }
}
