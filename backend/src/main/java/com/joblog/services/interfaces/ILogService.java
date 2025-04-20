package com.joblog.services.interfaces;

import com.joblog.models.request.LogRequest;

public interface ILogService {

  void addLogs(LogRequest logRequest) throws RuntimeException;
}
