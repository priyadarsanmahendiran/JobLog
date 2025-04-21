package com.joblog.models.request;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {

  public UUID userId;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public Date logDate;

  public List<String> tasksDone;

  public List<String> tasksPlanned;

  public List<String> blockers;
}
