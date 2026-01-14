package com.infosys.testingFramework.service;

import com.infosys.testingFramework.models.ExecutionRun;
import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.models.TestResult;
import com.infosys.testingFramework.repositories.ExecutionRunRepository;
import com.infosys.testingFramework.repositories.TestCaseRepository;
import com.infosys.testingFramework.repositories.TestResultRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExecutionService {

    private final TestCaseService testCaseService;
    private final TestResultRepository resultRepo;
    private final ExecutionRunRepository runRepo;

    public ExecutionService(
            TestCaseService testCaseService,
            TestResultRepository resultRepo,
            ExecutionRunRepository runRepo
    ) {
        this.testCaseService = testCaseService;
        this.resultRepo = resultRepo;
        this.runRepo = runRepo;
    }

    // ✅ EXISTING
    public void schedule(List<Long> testCaseIds) {
        ExecutionRun run = new ExecutionRun();
        run.setStatus("RUNNING");
        run.setStartedAt(LocalDateTime.now());
        runRepo.save(run);

        for (Long id : testCaseIds) {
            executeSingleTest(id, run);
        }

        run.setStatus("COMPLETED");
        runRepo.save(run);
    }

    // ✅ NEW — REQUIRED FOR UI
    public void scheduleByTag(String tag) {

        List<TestCase> cases = testCaseService.findByTag(tag);

        if (cases.isEmpty()) return;

        ExecutionRun run = new ExecutionRun();
        run.setStatus("RUNNING");
        run.setStartedAt(LocalDateTime.now());
        runRepo.save(run);

        for (TestCase tc : cases) {
            executeSingleTest(tc.getId(), run);
        }

        run.setStatus("COMPLETED");
        runRepo.save(run);
    }

    // 🔒 INTERNAL ENGINE
    private void executeSingleTest(Long testCaseId, ExecutionRun run) {

        TestCase tc = testCaseService.findAll()
                .stream()
                .filter(t -> t.getId().equals(testCaseId))
                .findFirst()
                .orElseThrow();

        TestResult result = new TestResult();
        result.setTestCase(tc);
        result.setExecutionRun(run);
        result.setExecutedAt(LocalDateTime.now());

        long start = System.currentTimeMillis();

        boolean passed = tc.getTarget() != null && !tc.getTarget().isBlank();

        long end = System.currentTimeMillis();

        result.setExecutionTime(end - start);
        result.setStatus(passed ? "PASS" : "FAIL");

        resultRepo.save(result);
    }
}
