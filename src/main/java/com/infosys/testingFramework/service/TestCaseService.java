package com.infosys.testingFramework.service;

import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.repositories.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    private final TestCaseRepository repo;

    public TestCaseService(TestCaseRepository repo) {
        this.repo = repo;
    }

    public TestCase save(TestCase tc) {
        return repo.save(tc);
    }

    public List<TestCase> findAll() {
        return repo.findAll();
    }

    public List<TestCase> findByTag(String tag) {
        return repo.findByTagIgnoreCase(tag);
    }
}
