package kea.exercise.hogwarts_api.repositories;

import kea.exercise.hogwarts_api.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
