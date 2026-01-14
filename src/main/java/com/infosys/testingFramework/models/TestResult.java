package com.infosys.testingFramework.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @ManyToOne
    @JoinColumn(name = "execution_run_id")
    private ExecutionRun executionRun;

    private String status;
    private Long executionTime;

    private LocalDateTime executedAt;

    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public ExecutionRun getExecutionRun() {
        return executionRun;
    }

    public void setExecutionRun(ExecutionRun executionRun) {
        this.executionRun = executionRun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }
}
