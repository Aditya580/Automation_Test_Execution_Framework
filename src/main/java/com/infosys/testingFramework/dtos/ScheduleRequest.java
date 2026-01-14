package com.infosys.testingFramework.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleRequest {
    private List<Long> testCaseIds;
}
