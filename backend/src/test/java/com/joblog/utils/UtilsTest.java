package com.joblog.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.joblog.TestBase;
import com.joblog.models.entities.Users;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class UtilsTest extends TestBase {

  @InjectMocks private Utils utils;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void transformRequestToWorklog() {
    LogRequest logRequest = prepareLogRequest();
    Users user = prepareUser();
    Worklog log = utils.transformRequestToWorklog(logRequest, user);
    Assertions.assertNotNull(log);
  }

  @Test
  void transformRequestToWorklogNoTasksDone() {
    LogRequest logRequest = prepareLogRequest();
    Users user = prepareUser();
    logRequest.setTasksDone(null);
    Worklog log = utils.transformRequestToWorklog(logRequest, user);
    Assertions.assertNotNull(log);
    Assertions.assertNull(log.getTasksDone());
  }

  @Test
  void transformRequestToWorklogNoTasksPlanned() {
    LogRequest logRequest = prepareLogRequest();
    Users user = prepareUser();
    logRequest.setTasksPlanned(null);
    Worklog log = utils.transformRequestToWorklog(logRequest, user);
    Assertions.assertNotNull(log);
    Assertions.assertNull(log.getTasksPlanned());
  }

  @Test
  void transformRequestToWorklogNoBlockers() {
    LogRequest logRequest = prepareLogRequest();
    Users user = prepareUser();
    logRequest.setBlockers(null);
    Worklog log = utils.transformRequestToWorklog(logRequest, user);
    Assertions.assertNotNull(log);
    Assertions.assertNull(log.getBlockers());
  }
}
