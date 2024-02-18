package kea.exercise.hogwarts_api.controllers;

import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {
    private StudentRepository repo;

    public StudentController(StudentRepository repo) {this.repo = repo;}

    @GetMapping
    public List<Student> getAll(){return repo.findAll();}

    @GetMapping("/{id}")
    public Student getOne(@PathVariable int id){return repo.findById(id).orElse(null);}

    @PostMapping
    public Student createOne(@RequestBody Student newStud) {
        return repo.save(newStud);
    }

    @PutMapping("/{id}")
    public Student updateOne(@PathVariable int id, @RequestBody Student updatedStudent) {
        Optional<Student> originalStudent = repo.findById(id);
        if (originalStudent.isPresent()){updatedStudent.setId(id); return repo.save(updatedStudent);}
        else {return null;}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteOne(@PathVariable int id) {
        Optional<Student> deleteThis = repo.findById(id);
        repo.deleteById(id);
        return ResponseEntity.of(deleteThis);
    }
}
