package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {

        return employeeService.findAll();
    }

    // add mapping for GET/ employee/ {employeeId}
    @GetMapping("/employees/{employeeId}")

    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {

            throw new RuntimeException("Employee id not found -" + employeeId);
        }
        return theEmployee;
    }


    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }

    //add maping for put/employees uppate exisiting employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee == null) {
            throw new RuntimeException("employee id not found " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "deleted employee id " + employeeId;
    }
}


