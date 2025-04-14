package utils;

import models.entities.Worklog;
import models.request.LogRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Utils {

    public Worklog transformRequestToWorklog(LogRequest logRequest){
        Worklog worklog = new Worklog();
        worklog.userId = Long.valueOf(logRequest.userId);
        worklog.blockers = String.join(",", logRequest.blockers);
        return worklog;
    }

}
