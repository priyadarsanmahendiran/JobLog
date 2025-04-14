package controllers.impl;

import controllers.interfaces.ILogController;
import lombok.extern.slf4j.Slf4j;
import models.request.LogRequest;
import models.response.LogResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import services.interfaces.ILogService;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class LogController implements ILogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private ILogService logService;

    @Override
    @PostMapping("/v1/addLog")
    public void addLogs(@RequestBody LogRequest logRequest) {
        log.info("Add Log Request for user:{} ", logRequest.userId);
        logService.addLogs(logRequest);
    }

    @Override
    @GetMapping("/v1/getLogs/{userId}")
    public List<LogResponse> getLogsForUser(@PathVariable String userId) {
        return null;
    }

    @Override
    @GetMapping("/v1/getLogs/{userId}")
    public List<LogResponse> getLogsForUserAndTimeRange(@PathVariable String userId, @RequestParam Date fromDate, @RequestParam Date toDate) {
        return null;
    }

    @Override
    @GetMapping("/v1/getLogs/{userId}/{date}")
    public LogResponse getLogsForUserAndDate(@PathVariable String userId, @PathVariable Date logDate) {
        return null;
    }

}
