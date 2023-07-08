package com.tolstsikava.testproject.demo.employee;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeWorkingHours(UUID id, LocalDateTime startTime, LocalDateTime endTime, int loggedTimeInMinutes) {

}
