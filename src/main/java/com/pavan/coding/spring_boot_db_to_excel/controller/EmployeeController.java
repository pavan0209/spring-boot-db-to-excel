package com.pavan.coding.spring_boot_db_to_excel.controller;

import com.pavan.coding.spring_boot_db_to_excel.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/download-data")
    public ResponseEntity<Resource> download() throws IOException {

        String fileName = "employees.xlsx";
        ByteArrayInputStream data = employeeService.getData();
        InputStreamResource file = new InputStreamResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/download-pdf")
    public ResponseEntity<Resource> downloadPdf() {

        String fileName = "employees.pdf";
        ByteArrayInputStream data = employeeService.getPdfData();
        InputStreamResource file = new InputStreamResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }
}
