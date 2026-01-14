package com.infosys.testingFramework.repositories;

import com.infosys.testingFramework.models.ExecutionRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionRunRepository extends JpaRepository<ExecutionRun, Long> {}
