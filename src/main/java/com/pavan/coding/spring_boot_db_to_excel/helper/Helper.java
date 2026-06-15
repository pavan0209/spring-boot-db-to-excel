package com.pavan.coding.spring_boot_db_to_excel.helper;

import com.pavan.coding.spring_boot_db_to_excel.model.Employee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Helper {

    public static String[] HEADERS = {"Id", "Name", "Salary"};

    public static String SHEET_NAME = "employee_data";

    public static ByteArrayInputStream dataToExcel(List<Employee> employees) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);

            for (int i = 0; i < HEADERS.length; i++) {
                row.createCell(i).setCellValue(HEADERS[i]);
            }
            int rowIndex = 1;
            for (Employee employee : employees) {
                Row dataRow = sheet.createRow(rowIndex++);

                dataRow.createCell(0).setCellValue(employee.getEmpId());
                dataRow.createCell(1).setCellValue(employee.getEmpName());
                dataRow.createCell(2).setCellValue(employee.getEmpSalary());
            }

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to export data to excel");
            return null;
        } finally {
            workbook.close();
            out.close();
        }
    }
}