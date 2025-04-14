package com.joblog.services.impl;

import lombok.extern.slf4j.Slf4j;
import com.joblog.models.entities.Worklog;
import com.joblog.models.request.LogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joblog.repositories.interfaces.IWorkLogRepository;
import com.joblog.services.interfaces.ILogService;
import com.joblog.utils.Utils;

@Service
@Slf4j
public class LogService implements ILogService {

    @Autowired
    private IWorkLogRepository workLogRepository;

    private Utils utils;

    @Override
    public void addLogs(LogRequest logRequest) {
        Worklog worklog = utils.transformRequestToWorklog(logRequest);
        workLogRepository.save(worklog);
    }
}
