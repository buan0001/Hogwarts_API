package kea.exercise.hogwarts_api.repositories;

import kea.exercise.hogwarts_api.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByFirstNameLikeIgnoreCase(String name);
    Optional<Student> findStudentByFirstNameContainingIgnoreCase(String name);

    // Optional<Student> findStudentByFirstNameLikeIgnoreCaseOrMiddleNameIgnoreCaseOrLastNameIgnoreCase(String name, String n2, String n3);
    Optional<Student> findStudentByFirstNameOrMiddleNameOrLastName(String first, String middle, String last);
    // Optional<Student> findStudentByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String first, String middle, String last);
}
