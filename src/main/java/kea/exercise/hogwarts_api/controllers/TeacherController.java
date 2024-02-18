package kea.exercise.hogwarts_api.controllers;

import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.models.Teacher;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import kea.exercise.hogwarts_api.repositories.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("teachers")
public class TeacherController {
    private TeacherRepository repo;

    public TeacherController(TeacherRepository repo) {this.repo = repo;}

    @GetMapping
    public List<Teacher> getAll(){return repo.findAll();}

    @GetMapping("/{id}")
    public Teacher getOne(@PathVariable int id){return repo.findById(id).orElse(null);}

    @PostMapping
    public Teacher createOne(@RequestBody Teacher newStud) {
        return repo.save(newStud);
    }

    @PutMapping("/{id}")
    public Teacher updateOne(@PathVariable int id, @RequestBody Teacher updatedTeacher) {
        Optional<Teacher> originalStudent = repo.findById(id);
        if (originalStudent.isPresent()){updatedTeacher.setId(id); return repo.save(updatedTeacher);}
        else {return null;}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteOne(@PathVariable int id) {
        Optional<Teacher> deleteThis = repo.findById(id);
        repo.deleteById(id);
        return ResponseEntity.of(deleteThis);
    }
}
