package com.joblog.controllers.interfaces;

import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/logs")
public interface IWorkLogController {

  ResponseEntity<String> addLogs(LogRequest logRequest);

  ResponseEntity<List<LogResponse>> getLogsForUser(String userId);

  ResponseEntity<List<LogResponse>> getLogsForUserAndTimeRange(
      String userId, LocalDate fromDate, LocalDate toDate);

  ResponseEntity<LogResponse> getLogsForUserAndDate(String userId, LocalDate logDate);
}
