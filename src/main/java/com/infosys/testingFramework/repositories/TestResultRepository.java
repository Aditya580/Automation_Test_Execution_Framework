package com.infosys.testingFramework.repositories;

import com.infosys.testingFramework.models.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    @Query("""
    SELECT CAST(tr.executedAt AS DATE) AS executionDate, COUNT(tr.id) AS failureCount
    FROM TestResult tr
    WHERE tr.status = 'FAIL'
    GROUP BY CAST(tr.executedAt AS DATE)
    """)
    List<Object[]> failureTrends();
}
