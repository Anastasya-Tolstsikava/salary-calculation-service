package com.tolstsikava.testproject.demo.client;

import com.tolstsikava.testproject.demo.employee.EmployeeWorkingHours;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeWorkingTimeInDayClient {

    @GetExchange("/period/{yearAndMonth}")
    List<EmployeeWorkingHours> getEmployeesWorkingTimeDuringPeriod(@PathVariable String yearAndMonth);
}
