package com.infosys.testingFramework.service;

import com.infosys.testingFramework.models.TestCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledExecutionService {

    private final TestCaseService testCaseService;
    private final ExecutionService executionService;

    public ScheduledExecutionService(
            TestCaseService testCaseService,
            ExecutionService executionService
    ) {
        this.testCaseService = testCaseService;
        this.executionService = executionService;
    }

    // Runs daily at 2 AM
    @Scheduled(cron = "0 0 2 * * ?")
    public void nightlyTestRun() {

        List<Long> testCaseIds = testCaseService.findAll()
                .stream()
                .map(TestCase::getId)
                .toList();

        if (!testCaseIds.isEmpty()) {
            executionService.schedule(testCaseIds);
        }
    }
}
