package com.joblog.schedulers;

import com.joblog.models.entities.Users;
import com.joblog.services.interfaces.ILogService;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class StandUpReportGenerator {

  @Autowired private ILogService logService;

  @Scheduled(cron = "0 9 * * *")
  public void generateReport() {
    log.info("Generating Standup Report for all active users");
    List<Users> activeUsers = logService.getAllActiveUsers();

    if (!activeUsers.isEmpty()) {
      for (Users users : activeUsers) {
        logService.generateStandupSummaryReport(users.getId(), LocalDate.now().minusDays(1));
      }
    }
    log.info("Standup Report Generation Completed");
  }
}
