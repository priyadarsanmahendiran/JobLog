package com.joblog;

import com.joblog.models.entities.Users;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

public class TestBase {

  public static LogRequest prepareLogRequest() {
    LogRequest logRequest = new LogRequest();
    logRequest.setUserId(UUID.randomUUID());
    logRequest.setLogDate(new Date());
    logRequest.setBlockers(Collections.singletonList("BLOCKER1"));
    logRequest.setTasksDone(Collections.singletonList("TASK1"));
    logRequest.setTasksPlanned(Collections.singletonList("TASK2"));
    return logRequest;
  }

  public static Users prepareUser() {
    Users users = new Users();
    users.setId(UUID.randomUUID());
    users.setName("Test User");
    users.setCreatedAt(new Date());
    users.setEmailId("test@gmail.com");
    return users;
  }

  public static Worklog prepareWorkLog() {
    Worklog worklog = new Worklog();
    worklog.setWorkLogId(UUID.randomUUID());
    worklog.setBlockers("BLOCKER1");
    worklog.setCreatedAt(new Date());
    worklog.setLogDate(new Date());
    worklog.setTasksPlanned("PLANNEDTASK1");
    worklog.setTasksDone("TASKDONE1");
    worklog.setUser(prepareUser());
    worklog.setLastUpdatedAt(new Date());
    return worklog;
  }
}
