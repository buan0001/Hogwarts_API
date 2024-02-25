package kea.exercise.hogwarts_api.services;

import kea.exercise.hogwarts_api.dtos.StudentRequestDTO;
import kea.exercise.hogwarts_api.dtos.StudentResponseDTO;
import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.repositories.HouseRepository;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository repo;
    private final HouseRepository houseRepository;

    public StudentService(StudentRepository repo, HouseRepository houseRepository) {
        this.repo = repo;
        this.houseRepository = houseRepository;
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

    private Student toEntity(StudentRequestDTO dto) {
        var entity = new Student();
        entity.setFirstName(dto.firstName());
        entity.setMiddleName(dto.middleName());
        entity.setLastName(dto.lastName());
        entity.setDateOfBirth(dto.dateOfBirth());
        entity.setEnrollmentYear(dto.enrollmentYear());
        entity.setGraduated(dto.graduated());
        entity.setHouse(houseRepository.findById(dto.house()).orElse(null));
        entity.setPrefect(dto.prefect());
        entity.setGraduationYear(dto.graduationYear());
        entity.setId(dto.id());
        return entity;
    }

    private StudentResponseDTO toDTO(Student entity) {


        return new StudentResponseDTO((entity.getId()), (entity.getFirstName()),
                (entity.getMiddleName()),
                (entity.getLastName()),
                (entity.getDateOfBirth()),
                (entity.getHouse().getName()),
                (entity.isPrefect()),
                (entity.getEnrollmentYear()),
                (entity.getGraduationYear()),
                (entity.isGraduated())


        );
    }

}
