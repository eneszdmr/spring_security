package com.cruddemo.employee.service;

import com.cruddemo.employee.entity.Employee;

import java.util.List;


public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(long id);

    Employee save(Employee employee);

    void deleteById(long id);
}
