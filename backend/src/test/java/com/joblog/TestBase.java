package com.joblog;

import com.joblog.models.entities.Users;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TestBase {

  public static LogRequest prepareLogRequest() {
    LogRequest logRequest = new LogRequest();
    logRequest.setLogDate(LocalDate.now());
    logRequest.setBlockers(Collections.singletonList("BLOCKER1"));
    logRequest.setTasksDone(Collections.singletonList("TASK1"));
    logRequest.setTasksPlanned(Collections.singletonList("TASK2"));
    return logRequest;
  }

  public static Users prepareUser() {
    Users users = new Users();
    users.setId(UUID.randomUUID());
    users.setName("Test User");
    users.setCreatedAt(LocalDateTime.now());
    users.setEmailId("test@gmail.com");
    users.setPassword("encodedPassword");
    return users;
  }

  public static Worklog prepareWorkLog() {
    Worklog worklog = new Worklog();
    worklog.setWorkLogId(UUID.randomUUID());
    worklog.setBlockers("BLOCKER1");
    worklog.setCreatedAt(LocalDateTime.now());
    worklog.setLogDate(LocalDate.now());
    worklog.setTasksPlanned("PLANNEDTASK1");
    worklog.setTasksDone("TASKDONE1");
    worklog.setUser(prepareUser());
    worklog.setLastUpdatedAt(LocalDateTime.now());
    return worklog;
  }

  public static List<LogResponse> getLogResponse() {
    LogResponse logResponse = new LogResponse();
    logResponse.setLogDate(LocalDate.now());
    logResponse.setTasksDone(Collections.singletonList("TASKDONE1"));
    logResponse.setTasksPlanned(Collections.singletonList("PLANNEDTASK1"));
    logResponse.setBlockers(Collections.singletonList("BLOCKER1"));
    return Collections.singletonList(logResponse);
  }
}
