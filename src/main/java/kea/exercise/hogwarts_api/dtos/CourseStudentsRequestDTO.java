package kea.exercise.hogwarts_api.dtos;

import kea.exercise.hogwarts_api.models.Student;

import java.util.List;

public class CourseStudentsRequestDTO {

    List<StudentIdRequestDTO> students;

    public CourseStudentsRequestDTO(List<StudentIdRequestDTO> students) {
        this.students = students;
    }
    public CourseStudentsRequestDTO(){}


    public List<StudentIdRequestDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentIdRequestDTO> studentIds) {
        this.students = studentIds;
    }
}
