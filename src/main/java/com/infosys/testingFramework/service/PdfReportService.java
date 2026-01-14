package com.infosys.testingFramework.service;

import com.infosys.testingFramework.models.TestResult;
import com.infosys.testingFramework.repositories.TestResultRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdfReportService {

    private final TestResultRepository repo;

    public PdfReportService(TestResultRepository repo) {
        this.repo = repo;
    }

    public void generatePdf(Document document) throws Exception {

        List<TestResult> results = repo.findAll();

        document.add(new Paragraph("Test Execution Report"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        table.addCell("Test Case");
        table.addCell("Status");
        table.addCell("Execution Time");
        table.addCell("Executed At");
        table.addCell("Run ID");

        for (TestResult r : results) {
            table.addCell(r.getTestCase().getName());
            table.addCell(r.getStatus());
            table.addCell(String.valueOf(r.getExecutionTime()));
            table.addCell(String.valueOf(r.getExecutedAt()));
            table.addCell(String.valueOf(r.getExecutionRun().getId()));
        }

        document.add(table);
    }
}
