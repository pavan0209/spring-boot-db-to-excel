package com.pavan.coding.spring_boot_db_to_excel.helper;

import com.pavan.coding.spring_boot_db_to_excel.model.Employee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.lowagie.text.PageSize;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

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

    public static ByteArrayInputStream dataToPdf(List<Employee> employees) {

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);

            Paragraph title = new Paragraph("Employee Report", font);
            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);

            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Salary");

            for (Employee employee : employees) {
                table.addCell(String.valueOf(employee.getEmpId()));
                table.addCell(employee.getEmpName());
                table.addCell(String.valueOf(employee.getEmpSalary()));
            }

            document.add(table);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            document.close();
        }
    }
}