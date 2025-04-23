package com.joblog.controllers.impl;

import com.joblog.commons.Constants;
import com.joblog.controllers.interfaces.IWorkLogController;
import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import com.joblog.services.interfaces.ILogService;
import com.joblog.utils.AuthUtil;
import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkLogController implements IWorkLogController {

  @Autowired private ILogService logService;
  @Autowired private AuthUtil authUtil;

  @Override
  @PostMapping("/v1/addLog")
  public ResponseEntity<String> addLogs(
      @RequestHeader String authHeader, @RequestBody LogRequest logRequest) {
    try {
      UUID userId = authUtil.getUserIdFromHeader(authHeader);
      logService.addLogs(logRequest, userId);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(Constants.LOGS_ADDED_SUCCESSFULLY, HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/v1/getLogs")
  public ResponseEntity<List<LogResponse>> getLogsForUser(@RequestHeader String authHeader) {
    UUID userId = authUtil.getUserIdFromHeader(authHeader);
    List<LogResponse> logResponses = logService.fetchLogsByUser(userId);
    if (CollectionUtils.isEmpty(logResponses)) {
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(logResponses, HttpStatus.OK);
    }
  }

  @Override
  @GetMapping(
      value = "/v1/getLogs",
      params = {"fromDate", "toDate"})
  public ResponseEntity<List<LogResponse>> getLogsForUserAndTimeRange(
      @RequestHeader String authHeader,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
    UUID userId = authUtil.getUserIdFromHeader(authHeader);
    List<LogResponse> logResponses =
        logService.fetchLogsByUserIdBetweenDates(userId, fromDate, toDate);
    if (logResponses.isEmpty())
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(logResponses, HttpStatus.OK);
  }

  @Override
  @GetMapping("/v1/getLogs/{logDate}")
  public ResponseEntity<LogResponse> getLogsForUserAndDate(
      @RequestHeader String authHeader,
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDate) {
    UUID userId = authUtil.getUserIdFromHeader(authHeader);
    LogResponse logResponses = logService.fetchLogsByUserIdAndDate(userId, logDate);
    if (Objects.isNull(logResponses)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(logResponses, HttpStatus.OK);
  }
}
