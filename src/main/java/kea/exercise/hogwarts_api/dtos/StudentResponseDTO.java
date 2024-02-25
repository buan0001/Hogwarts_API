package kea.exercise.hogwarts_api.dtos;

import java.time.LocalDate;

public record StudentResponseDTO
(     int id,
     String firstName,
     String middleName,
     String lastName,
     LocalDate dateOfBirth,
      String house,
     boolean prefect,
     int enrollmentYear,
     int graduationYear,
     boolean graduated

){}