package com.infosys.testingFramework.service;

import com.infosys.testingFramework.models.TestResult;
import com.infosys.testingFramework.repositories.TestResultRepository;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class ReportService {

    private final TestResultRepository repo;

    public ReportService(TestResultRepository repo) {
        this.repo = repo;
    }

    public void generateCsv(PrintWriter writer) {

        List<TestResult> results = repo.findAll();

        writer.println("TestCase,Status,ExecutionTime(ms),ExecutedAt,RunId");

        for (TestResult r : results) {
            writer.printf(
                    "%s,%s,%d,%s,%d%n",
                    r.getTestCase().getName(),
                    r.getStatus(),
                    r.getExecutionTime(),
                    r.getExecutedAt(),
                    r.getExecutionRun().getId()
            );
        }
    }
}
