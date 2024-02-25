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


    public Optional<StudentResponseDTO> updateIfExists( int id, StudentRequestDTO updatedStudent) {
        Optional<Student> originalStudent = repo.findById(id);
        if (originalStudent.isPresent()){
            Student entity = toEntity(updatedStudent);
            entity.setId(id);
            return Optional.of(toDTO(repo.save(entity)) ) ;
        }
        return Optional.empty();
    }
    public Optional<StudentResponseDTO> patchIfExists(int id, StudentRequestDTO changedProperties) {
        Optional<Student> originalStudent = repo.findById(id);
        if (originalStudent.isPresent()){
            return Optional.of(toDTO(repo.save(updateEntity(originalStudent.get(), changedProperties)))) ;
        }
       return Optional.empty();
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


        return new StudentResponseDTO(
              (entity.getId()),
//                (entity.getFirstName()),
//                (entity.getMiddleName()),
//                (entity.getLastName()),
                (entity.getFullName()),
                (entity.getDateOfBirth()),
                (entity.getHouse().getName()),
                (entity.isPrefect()),
                (entity.getEnrollmentYear()),
                (entity.getGraduationYear()),
                (entity.isGraduated())


        );
    }



    private Student updateEntity(Student entity, StudentRequestDTO dto) {
        if (dto.firstName() != null) {entity.setFirstName(dto.firstName());}
        if (dto.middleName() != null) {entity.setMiddleName(dto.middleName());}
        if (dto.lastName() != null) {entity.setLastName(dto.lastName());}
        if (dto.dateOfBirth() != null) {entity.setDateOfBirth(dto.dateOfBirth());}
        if (dto.house() != null) {entity.setHouse(houseRepository.findById(dto.house()).orElse(null) );}
        if (dto.prefect() != null) {entity.setPrefect(dto.prefect());}
        if (dto.enrollmentYear() != null) {entity.setEnrollmentYear(dto.enrollmentYear());}
        if (dto.graduationYear() != null) {entity.setGraduationYear(dto.graduationYear());}
        if (dto.graduated() != null) {entity.setGraduated(dto.graduated());}

        return entity;
    }
}
