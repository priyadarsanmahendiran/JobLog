package com.joblog.utils;

import com.joblog.models.entities.Users;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class Utils {

  public Worklog transformRequestToWorklog(LogRequest logRequest, Users user) {
    Worklog log = new Worklog();
    log.setUser(user);
    log.setLogDate(logRequest.getLogDate());
    if (Objects.nonNull(logRequest.getTasksDone()))
      log.setTasksDone(String.join(",", logRequest.getTasksDone()));
    if (Objects.nonNull(logRequest.getTasksPlanned()))
      log.setTasksPlanned(String.join(",", logRequest.getTasksPlanned()));
    if (Objects.nonNull(logRequest.getBlockers()))
      log.setBlockers(String.join(",", logRequest.getBlockers()));
    return log;
  }
}
