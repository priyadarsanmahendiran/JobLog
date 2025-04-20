package com.joblog.services.interfaces;

import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.request.LogRequest;

public interface ILogService {

  void addLogs(LogRequest logRequest) throws UserNotFoundException;
}
