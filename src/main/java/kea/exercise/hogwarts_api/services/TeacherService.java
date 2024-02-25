package kea.exercise.hogwarts_api.services;

import kea.exercise.hogwarts_api.dtos.StudentRequestDTO;
import kea.exercise.hogwarts_api.dtos.StudentResponseDTO;
import kea.exercise.hogwarts_api.dtos.TeacherRequestDTO;
import kea.exercise.hogwarts_api.dtos.TeacherResponseDTO;
import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.models.Teacher;
import kea.exercise.hogwarts_api.repositories.HouseRepository;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import kea.exercise.hogwarts_api.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repo;
    private final HouseRepository houseRepository;

    public TeacherService(TeacherRepository repo, HouseRepository houseRepository) {
        this.repo = repo;
        this.houseRepository = houseRepository;
    }

    public List<TeacherResponseDTO> findAll() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public Optional<TeacherResponseDTO> findById(int id) {
        return repo.findById(id).map(this::toDTO);
    }


    public Optional<TeacherResponseDTO> updateIfExists( int id, TeacherRequestDTO updatedTeacher) {
        Optional<Teacher> originalTeacher = repo.findById(id);
        if (originalTeacher.isPresent()){
            Teacher entity = toEntity(updatedTeacher);
            entity.setId(id);
            return Optional.of(toDTO(repo.save(entity)) ) ;
        }
        return Optional.empty();
    }
    public Optional<TeacherResponseDTO> patchIfExists(int id, TeacherRequestDTO changedProperties) {
        Optional<Teacher> originalTeacher = repo.findById(id);
        return originalTeacher.map(teacher -> toDTO(repo.save(updateEntity(teacher, changedProperties))));
    }

    public Optional<TeacherResponseDTO> deleteById(int id) {
        Optional<TeacherResponseDTO> existingTeacher = this.findById(id);
        repo.deleteById(id);
        return existingTeacher;
    }

    public TeacherResponseDTO save(TeacherRequestDTO newTeacher) {
        Teacher teacher = repo.save(toEntity(newTeacher));
        return toDTO(teacher);
    }

    private Teacher toEntity(TeacherRequestDTO dto) {
        var entity = new Teacher();
        entity.setId(dto.id());
        entity.setFirstName(dto.firstName());
        entity.setMiddleName(dto.middleName());
        entity.setLastName(dto.lastName());
        entity.setDateOfBirth(dto.dateOfBirth());
        entity.setHouse(houseRepository.findById(dto.house()).orElse(null));
        entity.setHeadOfHouse(dto.headOfHouse());
        entity.setEmployment(dto.employment());
        entity.setEmploymentStart(dto.employmentStart());
        entity.setEmploymentEnd(dto.employmentEnd());

        return entity;
    }

    private TeacherResponseDTO toDTO(Teacher entity) {
        return new TeacherResponseDTO(
                (entity.getId()),
                (entity.getFullName()),
                (entity.getDateOfBirth()),
                (entity.getHouse().getName()),
                (entity.isHeadOfHouse()),
                (entity.getEmployment()),
                (entity.getEmploymentStart()),
                (entity.getEmploymentEnd())


        );
    }



    private Teacher updateEntity(Teacher entity, TeacherRequestDTO dto) {
        if (dto.firstName() != null) {entity.setFirstName(dto.firstName());}
        if (dto.middleName() != null) {entity.setMiddleName(dto.middleName());}
        if (dto.lastName() != null) {entity.setLastName(dto.lastName());}
        if (dto.dateOfBirth() != null) {entity.setDateOfBirth(dto.dateOfBirth());}
        if (dto.house() != null) {entity.setHouse(houseRepository.findById(dto.house()).orElse(null) );}
        if (dto.headOfHouse() != null) {entity.setHeadOfHouse(dto.headOfHouse());}
        if (dto.employment() != null) {entity.setEmployment(dto.employment());}
        if (dto.employmentStart() != null) {entity.setEmploymentStart(dto.employmentStart());}
        if (dto.employmentEnd() != null) {entity.setEmploymentEnd(dto.employmentEnd());}

        return entity;
    }
}
