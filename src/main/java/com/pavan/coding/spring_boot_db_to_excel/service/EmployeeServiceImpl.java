package com.pavan.coding.spring_boot_db_to_excel.service;

import com.pavan.coding.spring_boot_db_to_excel.helper.Helper;
import com.pavan.coding.spring_boot_db_to_excel.model.Employee;
import com.pavan.coding.spring_boot_db_to_excel.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    public ByteArrayInputStream getData() throws IOException {
        List<Employee> employeeList = employeeRepository.findAll();
        return Helper.dataToExcel(employeeList);
    }
}
