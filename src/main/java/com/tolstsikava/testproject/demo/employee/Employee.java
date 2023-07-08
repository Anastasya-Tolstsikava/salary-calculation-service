package com.tolstsikava.testproject.demo.employee;

import com.tolstsikava.testproject.demo.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double salaryFactor;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

}
