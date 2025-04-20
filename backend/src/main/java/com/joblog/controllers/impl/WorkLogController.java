package com.joblog.controllers.impl;

import com.joblog.controllers.interfaces.IWorkLogController;
import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import com.joblog.services.interfaces.ILogService;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkLogController implements IWorkLogController {

  private static final Logger log = LoggerFactory.getLogger(WorkLogController.class);

  @Autowired private ILogService logService;

  @Override
  @PostMapping("/v1/addLog")
  public ResponseEntity<String> addLogs(@RequestBody LogRequest logRequest) {
    log.info("Add Log Request for user:{} ", logRequest.userId);
    try {
      logService.addLogs(logRequest);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    log.info("Log added successfully for user: {}", logRequest.userId);
    return new ResponseEntity<>("Log added successfully", HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/v1/getLogs/{userId}")
  public ResponseEntity<List<LogResponse>> getLogsForUser(@PathVariable String userId) {
    return null;
  }

  @Override
  @GetMapping(
      value = "/v1/getLogs/{userId}",
      params = {"fromDate", "toDate"})
  public ResponseEntity<List<LogResponse>> getLogsForUserAndTimeRange(
      @PathVariable String userId, @RequestParam Date fromDate, @RequestParam Date toDate) {
    return null;
  }

  @Override
  @GetMapping("/v1/getLogs/{userId}/{date}")
  public ResponseEntity<LogResponse> getLogsForUserAndDate(
      @PathVariable String userId, @PathVariable Date logDate) {
    return null;
  }
}
