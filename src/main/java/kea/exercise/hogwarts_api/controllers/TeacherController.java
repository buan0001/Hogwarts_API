package kea.exercise.hogwarts_api.controllers;


import kea.exercise.hogwarts_api.dtos.TeacherRequestDTO;
import kea.exercise.hogwarts_api.dtos.TeacherResponseDTO;
import kea.exercise.hogwarts_api.models.Teacher;
import kea.exercise.hogwarts_api.repositories.TeacherRepository;
import kea.exercise.hogwarts_api.services.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {this.service = service;}

    @GetMapping
    public List<TeacherResponseDTO> getAll(){return service.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO>  getOne(@PathVariable int id){
        return ResponseEntity.of(service.findById(id)) ;
    }

    @PostMapping
    public TeacherResponseDTO createOne(@RequestBody TeacherRequestDTO newTeacher) {
        return service.save(newTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateOne(@PathVariable int id, @RequestBody TeacherRequestDTO updatedTeacher) {
        return ResponseEntity.of(service.updateIfExists(id, updatedTeacher));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> changeYearOrPrefectStatus(@PathVariable int id, @RequestBody TeacherRequestDTO changedTeacher) {
        return ResponseEntity.of(service.patchIfExists(id, changedTeacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> deleteOne(@PathVariable int id) {
        return ResponseEntity.of(service.deleteById(id));
    }
}
