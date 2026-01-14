package com.infosys.testingFramework.service;

import com.infosys.testingFramework.repositories.TestResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final TestResultRepository repo;

    public AnalyticsService(TestResultRepository repo) {
        this.repo = repo;
    }

    public List<Map<String, Object>> failureTrends() {
        return repo.failureTrends().stream()
                .map(r -> Map.of(
                        "date", r[0],
                        "failures", r[1]
                ))
                .toList();
    }

    public Map<String, Long> executionSummary() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(
                        r -> r.getStatus(),
                        Collectors.counting()
                ));
    }
}
