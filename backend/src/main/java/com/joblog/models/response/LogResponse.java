package com.joblog.models.response;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class LogResponse {

  public String userId;

  public Date logDate;

  public List<String> tasksDone;

  public List<String> tasksPlanned;

  public List<String> blockers;
}
