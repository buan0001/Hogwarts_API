package kea.exercise.hogwarts_api.repositories;

import kea.exercise.hogwarts_api.models.Course;
import kea.exercise.hogwarts_api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
//    @Modifying
//    @Query(value = "delete from course_student where student_id = :student_id AND course_id = :course_id")
//    void deleteStudent(@Param(value = "course_id") int id, @Param(value = "student_id") int studentId);
//    @Modifying
//    @Query("DELETE FROM course_student s WHERE s.students_id=?1")
//    void findStudents(int studentId);
}
