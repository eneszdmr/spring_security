package com.cruddemo.employee.rest;

import com.cruddemo.employee.dao.EmployeeRepository;
import com.cruddemo.employee.entity.Employee;
import com.cruddemo.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private EmployeeRepository employeeDao;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, EmployeeRepository employeeDao) {
        this.employeeService = employeeService;
        this.employeeDao = employeeDao;
    }


    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable long id) {
        Employee employee = employeeService.findById(id);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeDao.saveAndFlush(employee);
    }

    @DeleteMapping("/employees/{id}")
    public Employee deleteEmployee(@PathVariable long id) {
        return employeeDao.deleteById(id);
    }


}
