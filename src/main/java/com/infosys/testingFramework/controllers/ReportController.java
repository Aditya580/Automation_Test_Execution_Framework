package com.infosys.testingFramework.controllers;

import com.infosys.testingFramework.service.PdfReportService;
import com.infosys.testingFramework.service.ReportService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;

@RestController
public class ReportController {

    private final ReportService reportService;
    private final PdfReportService pdfReportService;

    public ReportController(
            ReportService reportService,
            PdfReportService pdfReportService
    ) {
        this.reportService = reportService;
        this.pdfReportService = pdfReportService;
    }

    /* ================= CSV DOWNLOAD ================= */

    @GetMapping("/reports/csv")
    public void downloadCsv(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=test-execution-report.csv"
        );

        PrintWriter writer = response.getWriter();
        reportService.generateCsv(writer);
        writer.flush();
    }

    /* ================= PDF DOWNLOAD ================= */

    @GetMapping("/reports/pdf")
    public void downloadPdf(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=test-execution-report.pdf"
        );

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        pdfReportService.generatePdf(document);
        document.close();
    }
}
