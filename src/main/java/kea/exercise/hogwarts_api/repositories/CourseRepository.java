package kea.exercise.hogwarts_api.repositories;

import kea.exercise.hogwarts_api.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
