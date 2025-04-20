package com.joblog.services.impl;

import com.joblog.models.entities.Users;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import com.joblog.repositories.interfaces.IUserRepository;
import com.joblog.repositories.interfaces.IWorkLogRepository;
import com.joblog.services.interfaces.ILogService;
import com.joblog.utils.Utils;
import java.util.Optional;
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
  public void addLogs(LogRequest logRequest) throws RuntimeException {
    Optional<Users> user = userRepository.findById(logRequest.getUserId());
    if (user.isEmpty()) {
      log.error("User not found!: {}", logRequest.getUserId());
      throw new RuntimeException("User not found");
    }
    Worklog worklog = utils.transformRequestToWorklog(logRequest, user.get());
    workLogRepository.save(worklog);
  }
}
