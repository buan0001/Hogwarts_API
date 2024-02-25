package kea.exercise.hogwarts_api.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kea.exercise.hogwarts_api.models.EmpType;
import kea.exercise.hogwarts_api.models.House;

import java.time.LocalDate;

public record TeacherResponseDTO(int id, String fullName, LocalDate dateOfBirth,

                                 String house, Boolean headOfHouse,

                                 EmpType employment, LocalDate employmentStart, LocalDate employmentEnd) {
}
