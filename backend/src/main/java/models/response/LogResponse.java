package models.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LogResponse {

    public String userId;

    public Date logDate;

    public List<String> tasksDone;

    public List<String> tasksPlanned;

    public List<String> blockers;

}
