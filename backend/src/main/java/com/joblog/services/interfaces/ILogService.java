package com.joblog.services.interfaces;

import com.joblog.exceptions.UserNotFoundException;
import com.joblog.models.entities.Users;
import com.joblog.models.request.LogRequest;
import com.joblog.models.response.LogResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ILogService {

  void addLogs(LogRequest logRequest, UUID userId) throws UserNotFoundException;

  List<LogResponse> fetchLogsByUser(UUID userId);

  List<LogResponse> fetchLogsByUserIdBetweenDates(
      UUID userId, LocalDate fromDate, LocalDate toDate);

  LogResponse fetchLogsByUserIdAndDate(UUID userId, LocalDate logDate);

  List<Users> getAllActiveUsers();

  void generateStandupSummaryReport(UUID userId, LocalDate recordDate);
}
