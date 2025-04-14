package controllers.interfaces;

import models.request.LogRequest;
import models.response.LogResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RequestMapping(name = "/logs")
public interface ILogController {

    void addLogs(LogRequest logRequest);

    List<LogResponse> getLogsForUser(String userId);

    List<LogResponse> getLogsForUserAndTimeRange(String userId, Date fromDate, Date toDate);

    LogResponse getLogsForUserAndDate(String userId, Date logDate);

}
