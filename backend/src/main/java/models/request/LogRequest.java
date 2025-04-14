package models.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LogRequest {

    public String userId;

    public Date logDate;

    public List<String> tasksDone;

    public List<String> tasksPlanned;

    public List<String> blockers;

}
