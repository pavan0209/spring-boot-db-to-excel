package com.pavan.coding.spring_boot_db_to_excel.repository;

import com.pavan.coding.spring_boot_db_to_excel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
