package kea.exercise.hogwarts_api.dtos;

import java.time.LocalDate;

public record StudentRequestDTO
        (int id,
         String firstName,
         String middleName,
         String lastName,
         String fullName,
         LocalDate dateOfBirth,
         Boolean prefect,
         Integer enrollmentYear,
         Integer graduationYear,
         Boolean graduated,
         String house) {
    public StudentRequestDTO {
        if (fullName != null) {
            String[] nameParts = fullName.split(" ");
            firstName = nameParts[0];
            lastName = nameParts[nameParts.length-1];
            if (nameParts.length > 2) {
                StringBuilder middleNameBuilder = new StringBuilder();
                for (int i = 1; i < nameParts.length-1; i++) {
                    if (i < nameParts.length-2) {
                        middleNameBuilder.append(nameParts[i]).append(" ");
                    }
                    else {
                        middleNameBuilder.append(nameParts[i]);}
                }
                middleName = middleNameBuilder.toString();

            }
        }
    }
}
