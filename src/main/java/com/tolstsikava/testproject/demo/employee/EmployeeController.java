package com.tolstsikava.testproject.demo.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/1.0/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{teamName}/salary")
    public List<EmployeesSalaryDto> getEmployeesByTeamWithSalary(@PathVariable String teamName, @RequestParam Long totalAmount) {
        return employeeService.getEmployeesByTeamWithSalary(teamName, totalAmount);
    }
}
