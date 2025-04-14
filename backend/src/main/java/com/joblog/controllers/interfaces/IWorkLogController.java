package com.joblog.controllers.interfaces;

import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RequestMapping(name = "/logs")
public interface IWorkLogController {

    void addLogs(LogRequest logRequest);

    List<LogResponse> getLogsForUser(String userId);

    List<LogResponse> getLogsForUserAndTimeRange(String userId, Date fromDate, Date toDate);

    LogResponse getLogsForUserAndDate(String userId, Date logDate);

}
