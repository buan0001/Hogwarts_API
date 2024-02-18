package kea.exercise.hogwarts_api.repositories;

import kea.exercise.hogwarts_api.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
