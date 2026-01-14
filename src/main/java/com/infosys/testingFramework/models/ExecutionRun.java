package com.infosys.testingFramework.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
public class ExecutionRun {

    @Id
    @GeneratedValue
    private Long id;

    private String status; // RUNNING, COMPLETED

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
}
