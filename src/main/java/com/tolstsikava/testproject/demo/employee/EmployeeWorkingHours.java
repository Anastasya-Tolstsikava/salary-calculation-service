package com.tolstsikava.testproject.demo.employee;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeWorkingHours(UUID id, LocalDateTime startTime, LocalDateTime endTime, int loggedTimeInMinutes) {
//    public static final EmployeeWorkingHours DEFAULT = new EmployeeWorkingHours(, null, null, 0);
}
