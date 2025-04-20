package com.joblog.models.request;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
public class LogRequest {

  public UUID userId;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public Date logDate;

  public List<String> tasksDone;

  public List<String> tasksPlanned;

  public List<String> blockers;
}
