package com.thenriquedb.jasperreports.services;

import com.thenriquedb.jasperreports.domain.Student;
import net.sf.jasperreports.engine.*;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class CertificateService {
    public static final String REPORT_PATH = "classpath:jasper/certificates/";
    public static final String REPORT_NAME = "certificate_template.jrxml";
    public static final String REPORT_OUTPUT = "temp/jasper/outputs/";
    public static final String REPORT_BG = "classpath:img/bg-certificate.png";

    public void generateReport(Student student) throws IOException, JRException {
        String reportPath = ResourceUtils.getFile(REPORT_PATH + REPORT_NAME).getAbsolutePath();
        byte[] imageBytes = loadImage();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", student.getName());
        parameters.put("course", student.getCourse());
        parameters.put("courseDuration", student.getCourseDuration());
        parameters.put("startDate", student.getStartDate());
        parameters.put("endDate", student.getEndDate());
        parameters.put("imgBg", imageBytes);

        try {
            JasperReport jasper = JasperCompileManager.compileReport(reportPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, new JREmptyDataSource());
            String outputPath = ResourceUtils.getFile(REPORT_OUTPUT).getAbsolutePath();
            String filename = student.getName().replaceAll(" ", "_").toLowerCase();
            String  fullPath = outputPath + "/" + filename + ".pdf";

            JasperExportManager.exportReportToPdfFile(jasperPrint, fullPath);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private byte[] loadImage() throws IOException {
        String bgPath = ResourceUtils.getFile(REPORT_BG).getAbsolutePath();
        File bgFile = new File(bgPath);

        if (!bgFile.exists()) {
            throw new IOException("Background image not found: " + bgPath);
        }

        try(InputStream inputStream = new FileInputStream(bgFile)) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
