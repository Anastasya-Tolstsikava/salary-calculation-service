package com.tolstsikava.testproject.demo.employee;

import com.tolstsikava.testproject.demo.client.EmployeeWorkingTimeInDayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final String YEAR_AND_MONTH = "2023-03";

    private final EmployeeWorkingTimeInDayClient employeeWorkingTimeInDayClient;

    private final EmployeeRepository employeeRepository;

    public List<EmployeesSalaryDto> getEmployeesByTeamWithSalary(String teamName, Long moneyAmount) {
        var teamEmployees = employeeRepository.findAllByTeamName(teamName);
        if (teamEmployees.isEmpty()){
            return Collections.emptyList();
        }

        var workingHoursById = employeeWorkingTimeInDayClient.getEmployeesWorkingTimeDuringPeriod(YEAR_AND_MONTH)
                .stream()
                .collect(Collectors.groupingBy(EmployeeWorkingHours::id));

        var conventionalWorkingUnitsSumForTeamEmployees = getConventionalWorkingUnitsById(workingHoursById, teamEmployees);
        var conventionalWorkingUnitSalary = calculateConventionalWorkingUnitSalary(conventionalWorkingUnitsSumForTeamEmployees, moneyAmount);

        return conventionalWorkingUnitsSumForTeamEmployees.entrySet()
                .stream()
                .map(e -> new EmployeesSalaryDto(e.getKey(), e.getValue() * conventionalWorkingUnitSalary))
                .collect(Collectors.toList());
    }

    private double calculateConventionalWorkingUnitSalary(Map<UUID, Double> employeesWorkingHours, Long moneyAmount) {
        var sum = employeesWorkingHours.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return moneyAmount / sum;
    }

    private Map<UUID, Double> getConventionalWorkingUnitsById(Map<UUID, List<EmployeeWorkingHours>> employeesWorkingHours, List<Employee> employeesInTeam) {
        return employeesInTeam.stream()
                .map(employee -> {
                    var employeeWorkingHours = employeesWorkingHours.getOrDefault(employee.getId(), Collections.emptyList());
                    var employeeTotalMinutes = calculateTotalMinutes(employeeWorkingHours);
                    return Pair.of(employee.getId(), employeeTotalMinutes * employee.getSalaryFactor());
                }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }


    private Integer calculateTotalMinutes(List<EmployeeWorkingHours> employeeWorkingHours) {
        return employeeWorkingHours
                .stream()
                .reduce(0,
                        (accumulator, o2) -> accumulator + o2.loggedTimeInMinutes(),
                        Integer::sum);
    }
}
