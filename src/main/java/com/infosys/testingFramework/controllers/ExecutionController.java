package com.infosys.testingFramework.controllers;

import com.infosys.testingFramework.repositories.TestResultRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExecutionController {

    private final TestResultRepository testResultRepository;

    public ExecutionController(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    @GetMapping("/execution")
    public String executionPage(Model model) {
        model.addAttribute("results", testResultRepository.findAll());
        return "execution";
    }
}
