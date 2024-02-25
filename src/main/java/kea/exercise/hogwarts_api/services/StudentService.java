package kea.exercise.hogwarts_api.services;

import kea.exercise.hogwarts_api.dtos.StudentRequestDTO;
import kea.exercise.hogwarts_api.dtos.StudentResponseDTO;
import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<StudentResponseDTO> findAll() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public Optional<StudentResponseDTO> findById(int id) {
        return repo.findById(id).map(this::toDTO);
    }


    public Optional<Student> updateIfExists( int id, Student updatedStudent) {
        Optional<Student> originalStudent = repo.findById(id);
        if (originalStudent.isPresent()){
            updatedStudent.setId(id); repo.save(updatedStudent);
        }
        return originalStudent;
    }

    public Optional<StudentResponseDTO> deleteById(int id) {
        Optional<StudentResponseDTO> existingStudent = this.findById(id);
        repo.deleteById(id);
        return existingStudent;
    }

    public StudentResponseDTO save(StudentRequestDTO newStud) {
        Student newStudent = repo.save(toEntity(newStud));
        return toDTO(newStudent);
    }

    private Student toEntity(StudentRequestDTO newStud) {
        return new Student()
    }

    private StudentResponseDTO toDTO(Student entity) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setEnrollmentYear(entity.getEnrollmentYear());
        dto.setGraduated(entity.isGraduated());
        dto.setHouse(entity.getHouse().getName());
        dto.setPrefect(entity.isPrefect());
        dto.setGraduationYear(entity.getGraduationYear());
        dto.setId(entity.getId());

        return dto;
    }

}
