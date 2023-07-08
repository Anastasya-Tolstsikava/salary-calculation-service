package com.tolstsikava.testproject.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findAllByTeamName(String teamName);
}
