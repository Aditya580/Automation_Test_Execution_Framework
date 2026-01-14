//package com.infosys.testingFramework.execution;
//
//import com.infosys.testingFramework.models.ExecutionRun;
//import com.infosys.testingFramework.models.TestCase;
//import com.infosys.testingFramework.models.TestResult;
//import com.infosys.testingFramework.repositories.TestResultRepository;
//
//import java.time.LocalDateTime;
//
//public class ExecutionTask implements Runnable {
//
//    private final TestCase testCase;
//    private final ExecutionRun run;
//    private final TestResultRepository resultRepo;
//
//    public ExecutionTask(
//            TestCase testCase,
//            ExecutionRun run,
//            TestResultRepository resultRepo
//    ) {
//        this.testCase = testCase;
//        this.run = run;
//        this.resultRepo = resultRepo;
//    }
//
//    @Override
//    public void run() {
//
//        TestResult result = new TestResult();
//        result.setTestCase(testCase);          // ✅ FIX HERE
//        result.setExecutionRun(run);
//        result.setExecutedAt(LocalDateTime.now());
//        result.setStatus("RUNNING");
//
//        long start = System.currentTimeMillis();
//
//        try {
//            // 🔥 Simulated execution
//            boolean passed = testCase.getTarget() != null
//                    && !testCase.getTarget().isBlank();
//
//            long end = System.currentTimeMillis();
//            result.setExecutionTime(end - start);
//            result.setStatus(passed ? "PASS" : "FAIL");
//
//        } catch (Exception e) {
//            result.setStatus("FAIL");
//            result.setExecutionTime(0);
//        }
//
//        resultRepo.save(result);
//    }
//}
