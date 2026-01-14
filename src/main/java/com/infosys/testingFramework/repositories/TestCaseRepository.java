package com.infosys.testingFramework.repositories;

import com.infosys.testingFramework.models.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    // ✅ REQUIRED FOR SEARCH FEATURE
    List<TestCase> findByNameContainingIgnoreCase(String name);

    // ✅ REQUIRED FOR TAG FEATURE
    List<TestCase> findByTagIgnoreCase(String tag);
}
