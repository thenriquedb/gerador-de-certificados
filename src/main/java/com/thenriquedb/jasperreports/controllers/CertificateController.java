package com.thenriquedb.jasperreports.controllers;

import com.thenriquedb.jasperreports.domain.Student;
import com.thenriquedb.jasperreports.services.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    CertificateService reportService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateReport(@RequestBody Student student) {
        try {
            String filePath = reportService.generateReport(student);

            File file = new File(filePath);
            Path path = Paths.get(file.getAbsolutePath());
            var resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(file.length())
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
