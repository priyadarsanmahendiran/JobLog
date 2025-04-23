package com.joblog.controllers.impl;

import com.joblog.commons.Constants;
import com.joblog.controllers.interfaces.IWorkLogController;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import com.joblog.services.interfaces.ILogService;
import java.time.LocalDate;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkLogController implements IWorkLogController {

  @Autowired private ILogService logService;

  @Override
  @PostMapping("/v1/addLog")
  public ResponseEntity<String> addLogs(@RequestBody LogRequest logRequest) {
    try {
      logService.addLogs(logRequest);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(Constants.LOGS_ADDED_SUCCESSFULLY, HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/v1/getLogs/{userId}")
  public ResponseEntity<List<LogResponse>> getLogsForUser(@PathVariable String userId) {
    List<LogResponse> logResponses = logService.fetchLogsByUser(UUID.fromString(userId));
    if (CollectionUtils.isEmpty(logResponses)) {
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(logResponses, HttpStatus.OK);
    }
  }

  @Override
  @GetMapping(
      value = "/v1/getLogs/{userId}",
      params = {"fromDate", "toDate"})
  public ResponseEntity<List<LogResponse>> getLogsForUserAndTimeRange(
      @PathVariable String userId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
    List<LogResponse> logResponses =
        logService.fetchLogsByUserIdBetweenDates(UUID.fromString(userId), fromDate, toDate);
    if (logResponses.isEmpty())
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(logResponses, HttpStatus.OK);
  }

  @Override
  @GetMapping("/v1/getLogs/{userId}/{logDate}")
  public ResponseEntity<LogResponse> getLogsForUserAndDate(
      @PathVariable String userId,
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDate) {
    LogResponse logResponses =
        logService.fetchLogsByUserIdAndDate(UUID.fromString(userId), logDate);
    if (Objects.isNull(logResponses)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(logResponses, HttpStatus.OK);
  }
}
