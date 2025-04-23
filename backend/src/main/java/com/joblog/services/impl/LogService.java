package com.joblog.services.impl;

import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.entities.Users;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import com.joblog.repositories.interfaces.IUserRepository;
import com.joblog.repositories.interfaces.IWorkLogRepository;
import com.joblog.services.interfaces.ILogService;
import com.joblog.utils.Utils;
import java.time.LocalDate;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogService implements ILogService {

  @Autowired private IWorkLogRepository workLogRepository;

  @Autowired private IUserRepository userRepository;

  @Autowired private Utils utils;

  @Override
  public void addLogs(LogRequest logRequest) throws UserNotFoundException {
    log.info("Add Log Request for user:{} ", logRequest.userId);
    Optional<Users> user = userRepository.findById(logRequest.getUserId());
    if (user.isEmpty()) {
      log.error("User not found!: {}", logRequest.getUserId());
      throw new UserNotFoundException(logRequest.getUserId());
    }
    Worklog worklog = utils.transformRequestToWorklog(logRequest, user.get());
    workLogRepository.save(worklog);
  }

  @Override
  public List<LogResponse> fetchLogsByUser(UUID userId) {
    log.info("Fetching work logs for user: {}", userId);
    Optional<List<Worklog>> worklogByUserOpt = workLogRepository.findByUserId(userId);
    if (worklogByUserOpt.isEmpty()) {
      log.info("No logs found for user: {}", userId);
      return Collections.emptyList();
    }
    List<Worklog> worklogs = worklogByUserOpt.get();
    return utils.transformWorkLogToResponse(worklogs);
  }

  @Override
  public List<LogResponse> fetchLogsByUserIdBetweenDates(
      UUID userId, LocalDate fromDate, LocalDate toDate) {
    log.info("Fetching logs for user: {} between dates: {} and {}", userId, fromDate, toDate);
    Optional<List<Worklog>> worklogByUserOpt =
        workLogRepository.findByUserIdAndLogDateBetween(userId, fromDate, toDate);
    if (worklogByUserOpt.isEmpty()) {
      log.info("No logs present for user: {} between dates: {} and {}", userId, fromDate, toDate);
      return Collections.emptyList();
    }
    return utils.transformWorkLogToResponse(worklogByUserOpt.get());
  }

  @Override
  public LogResponse fetchLogsByUserIdAndDate(UUID userId, LocalDate logDate) {
    log.info("Fetching logs for user: {} on date: {}", userId, logDate);
    Optional<Worklog> worklogByUserOpt = workLogRepository.findByUserIdAndLogDate(userId, logDate);
    if (worklogByUserOpt.isEmpty()) {
      log.info("No logs present for user: {} on date: {}", userId, logDate);
      return null;
    }
    return utils.transformWorkLogToResponse(worklogByUserOpt.get());
  }
}
