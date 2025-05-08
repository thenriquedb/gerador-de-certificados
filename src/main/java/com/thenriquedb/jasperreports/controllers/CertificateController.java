package com.thenriquedb.jasperreports.controllers;

import com.thenriquedb.jasperreports.domain.Student;
import com.thenriquedb.jasperreports.services.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    CertificateService reportService;

    @PostMapping("/generate")
    public String generateReport(@RequestBody Student student) {
        try {
            reportService.generateReport(student);
            return "Report generated successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating report: " + e.getMessage();
        }
    }
}
