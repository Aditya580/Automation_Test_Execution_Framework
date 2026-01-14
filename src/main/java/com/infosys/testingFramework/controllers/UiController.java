package com.infosys.testingFramework.controllers;

import com.infosys.testingFramework.models.TestCase;
import com.infosys.testingFramework.repositories.TestResultRepository;
import com.infosys.testingFramework.service.AnalyticsService;
import com.infosys.testingFramework.service.ExecutionService;
import com.infosys.testingFramework.service.TestCaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class UiController {

    private final TestCaseService testCaseService;
    private final ExecutionService executionService;
    private final AnalyticsService analyticsService;
    private final TestResultRepository resultRepo;

    public UiController(
            TestCaseService testCaseService,
            ExecutionService executionService,
            AnalyticsService analyticsService,
            TestResultRepository resultRepo
    ) {
        this.testCaseService = testCaseService;
        this.executionService = executionService;
        this.analyticsService = analyticsService;
        this.resultRepo = resultRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("testCases", testCaseService.findAll());
        model.addAttribute("testCase", new TestCase());
        model.addAttribute("tags", List.of("SMOKE", "REGRESSION", "SANITY"));
        return "dashboard";
    }


    @PostMapping("/testcase")
    public String createTestCase(@ModelAttribute TestCase testCase) {
        testCaseService.save(testCase);   // ✅ SAME SERVICE AS REST
        return "redirect:/ui/dashboard";
    }

    @PostMapping("/run")
    public String runTests(@RequestParam List<Long> testCaseIds) {
        executionService.schedule(testCaseIds); // ✅ SAME API LOGIC
        return "redirect:/ui/execution";
    }

    @GetMapping("/execution")
    public String execution(Model model) {
        model.addAttribute("results", resultRepo.findAll());
        return "execution";
    }
    @PostMapping("/run/tag")
    public String runByTag(@RequestParam String tag) {
        executionService .scheduleByTag(tag);
        return "redirect:/ui/execution";
    }



    @GetMapping("/analytics")
    public String analytics(Model model) {
        model.addAttribute("trends", analyticsService.failureTrends());
        return "analytics";
    }
}
