package kea.exercise.hogwarts_api.controllers;

import kea.exercise.hogwarts_api.models.Course;
import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.models.Teacher;
import kea.exercise.hogwarts_api.repositories.CourseRepository;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("courses")
public class CourseController {
    private CourseRepository repo;
    private StudentRepository studentRepo;

    public CourseController(CourseRepository repo, StudentRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public List<Course> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Course getOneCourse(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/{id}/teacher")
    public Teacher getOneTeacherByCourse(@PathVariable int id) {
        Optional<Course> course = repo.findById(id);
        if (course.isPresent()) {
            return course.get().getTeacher();
        } else {
            return null;
        }
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsByCourse(@PathVariable int id) {
        Optional<Course> course = repo.findById(id);
        if (course.isPresent()) {
            return course.get().getStudents();
        } else {
            return null;
        }
    }

    @PostMapping
    public Course createOne(@RequestBody Course newCourse) {
        return repo.save(newCourse);
    }

    @PutMapping("/{id}")
    public Course updateFullCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        Optional<Course> originalCourse = repo.findById(id);
        if (originalCourse.isPresent()) {
            updatedCourse.setId(id);
            return repo.save(updatedCourse);
        } else {
            return null;
        }
    }

    @PutMapping("/{id}/students/{studentId}")
    public Course addStudentToCourse(@PathVariable int id, @PathVariable int studentId) {
        Optional<Course> originalCourse = repo.findById(id);
        if (originalCourse.isPresent()) {
            Optional<Student> foundStudent = studentRepo.findById(studentId);
            if (foundStudent.isPresent()) {
                originalCourse.get().getStudents().add(foundStudent.get());
                return repo.save(originalCourse.get());
            }
        }
        return null;
    }

    @PutMapping("/{id}/teacher")
    public Course updateOne(@PathVariable int id, @RequestBody Teacher newTeacher) {
        Optional<Course> originalCourse = repo.findById(id);
        if (originalCourse.isPresent()) {
                originalCourse.get().setTeacher(newTeacher);
                return repo.save(originalCourse.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id) {
        Optional<Course> deleteThis = repo.findById(id);
        repo.deleteById(id);
        return ResponseEntity.of(deleteThis);
    }

    @DeleteMapping("/{id}/teacher")
    public ResponseEntity<Course> deleteTeacherFromCourse(@PathVariable int id) {
        Optional<Course> foundCourse = repo.findById(id);
        if (foundCourse.isPresent()) {
            foundCourse.get().setTeacher(null);
            repo.save(foundCourse.get());
        }
        return ResponseEntity.of(foundCourse);
    }



    @DeleteMapping("/{id}/students/{studentId}")

    public ResponseEntity<Course> deleteStudentFromCourse(@PathVariable int id, @PathVariable int studentId) {
        Optional<Course> foundCourse = repo.findById(id);
        if (foundCourse.isPresent()){
            Course course = foundCourse.get();
            course.getStudents().removeIf(student -> student.getId() == studentId);
           repo.save(course);
        }
        return ResponseEntity.of(foundCourse);
    }
}
