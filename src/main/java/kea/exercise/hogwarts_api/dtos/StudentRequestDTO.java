package kea.exercise.hogwarts_api.dtos;

import java.time.LocalDate;

public record StudentRequestDTO
        (int id,
         String firstName,
         String middleName,
         String lastName,
         LocalDate dateOfBirth,
         boolean prefect,
         int enrollmentYear,
         int graduationYear,
         boolean graduated,
         String house) {
}
