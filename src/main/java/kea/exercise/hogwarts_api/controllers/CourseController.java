package kea.exercise.hogwarts_api.controllers;

import kea.exercise.hogwarts_api.dtos.CourseStudentsRequestDTO;
import kea.exercise.hogwarts_api.models.Course;
import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.models.Teacher;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import kea.exercise.hogwarts_api.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("courses")
public class CourseController {
    private CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getOneCourse(@PathVariable int id) {
        return ResponseEntity.of(service.findById(id)) ;
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<Teacher> getOneTeacherByCourse(@PathVariable int id) {
        return ResponseEntity.of(service.getTeacherFromCourse(id)) ;
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student> > getStudentsByCourse(@PathVariable int id) {
        return ResponseEntity.of(service.studentsFromCourse(id)) ;
    }

    @PostMapping
    public ResponseEntity<Course> createOne(@RequestBody Course newCourse) {
        return ResponseEntity.of(service.save(newCourse)) ;
    }

    @PostMapping("/{id}/students")
    public ResponseEntity<Set<Student>> addStudentsToCourse(@PathVariable int id, @RequestBody CourseStudentsRequestDTO studentIds) {
        return ResponseEntity.of(service.addStudentsToCourse(id, studentIds)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateFullCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        return ResponseEntity.of(service.updateFullCourse(id, updatedCourse)) ;
    }



    @PatchMapping("/{id}/teacher")
    public ResponseEntity<Teacher> updateOne(@PathVariable int id, @RequestBody Teacher newTeacher) {
        return ResponseEntity.of(service.changeTeacher(id, newTeacher)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id) {
        return ResponseEntity.of(service.delete(id));
    }




    @DeleteMapping("/{id}/students/{studentId}")
    public ResponseEntity<Student> deleteStudentFromCourse(@PathVariable int id, @PathVariable int studentId) {
        return ResponseEntity.of(service.removeStudent(id, studentId)) ;
    }
}
