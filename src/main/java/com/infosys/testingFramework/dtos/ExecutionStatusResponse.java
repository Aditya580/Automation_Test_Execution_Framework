package com.infosys.testingFramework.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExecutionStatusResponse {
    private Long runId;
    private String status;
}
