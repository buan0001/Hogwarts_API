package kea.exercise.hogwarts_api.controllers;

import kea.exercise.hogwarts_api.dtos.StudentRequestDTO;
import kea.exercise.hogwarts_api.dtos.StudentResponseDTO;
import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {this.service = service;}

    @GetMapping
    public List<StudentResponseDTO> getAll(){return service.findAll();}

    @GetMapping("/{id}")
    public StudentResponseDTO getOne(@PathVariable int id){return service.findById(id).orElse(null);}

    @PostMapping
    public StudentResponseDTO createOne(@RequestBody StudentRequestDTO newStud) {
        return service.save(newStud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateOne(@PathVariable int id, @RequestBody Student updatedStudent) {
        return ResponseEntity.of(service.updateIfExists(id, updatedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> deleteOne(@PathVariable int id) {
        return ResponseEntity.of(service.deleteById(id));
    }
}
