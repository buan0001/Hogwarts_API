package kea.exercise.hogwarts_api.services;

//import jakarta.transaction.Transactional;
import kea.exercise.hogwarts_api.dtos.CourseStudentsRequestDTO;
import kea.exercise.hogwarts_api.dtos.StudentIdRequestDTO;
import kea.exercise.hogwarts_api.models.Course;
import kea.exercise.hogwarts_api.models.Student;
import kea.exercise.hogwarts_api.models.Teacher;
import kea.exercise.hogwarts_api.repositories.CourseRepository;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import kea.exercise.hogwarts_api.repositories.TeacherRepository;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(isolation = Isolation.READ_UNCOMMITTED)
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    public Optional<Teacher> getTeacherFromCourse(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(Course::getTeacher);
    }

    public Optional<Set<Student>> studentsFromCourse(int id) {

        Optional<Course> course = courseRepository.findById(id);
        return course.map(Course::getStudents);
    }
    //@Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Optional<Course> save(Course newCourse) {
        Course savedCourse = courseRepository.save(newCourse);

        // tried multiple things, only this work around did the job :/
        Teacher teacher = teacherRepository.findById(newCourse.getTeacher().getId()).orElse(null);
        List<Student> students = studentRepository.findAllById(newCourse.getStudents().stream().map(Student::getId).toList());
        //List<Student> students = studentRepository.findAllById(newCourse.getStudents().stream().map(Student::getId).collect(Collectors.toSet()));

        savedCourse.setTeacher(teacher);
        //Set<Student> studs = new HashSet<>(students);
        savedCourse.setStudents(new HashSet<>(students));

        return Optional.of(savedCourse) ;
//        Course newOne = courseRepository.save(newCourse);
//        return courseRepository.findById(newOne.getId());
//        //return findById(newOne.getId());
    }

    public Optional<Course> updateFullCourse(int id, Course updatedCourse) {

        Optional<Course> originalCourse = courseRepository.findById(id);
        if (originalCourse.isPresent()) {
            updatedCourse.setId(id);
            courseRepository.save(updatedCourse);
            return originalCourse;
        }
        return Optional.empty();

    }

    public Optional<Set<Student>> addStudentsToCourse(int id, CourseStudentsRequestDTO req) {
        Optional<Course> originalCourse = courseRepository.findById(id);
        if (originalCourse.isPresent()) {
            Course foundCourse = originalCourse.get();
            List<StudentIdRequestDTO> studentIds = req.getStudents();
            for (StudentIdRequestDTO student : studentIds) {
                Optional<Student> foundStudent;
                if (student.getName() != null) {
                    foundStudent = studentRepository.findStudentByFirstNameContainingIgnoreCase(student.getFirstName());
                    //(student.getFirstName(), student.getMiddleName(),student.getLastName());
//                    if (foundStudent.get().getSchoolYear() == foundCourse.getSchoolYear())
//                    {
                } else {
                    foundStudent = studentRepository.findById(student.getId());

                }
                foundStudent.ifPresent(newStudent -> {
                    if (foundStudent.get().getSchoolYear() == foundCourse.getSchoolYear()) {
                        foundCourse.getStudents().add(newStudent);
                    }
                    else {
                        System.out.println(foundStudent.get().getSchoolYear());
                        System.out.println(foundCourse.getSchoolYear());
                    }
                });
            }
            courseRepository.save(originalCourse.get());
        }

        return originalCourse.map(Course::getStudents);
    }

    public Optional<Teacher> changeTeacher(int id, Teacher newTeacher) {
        Optional<Course> originalCourse = courseRepository.findById(id);
        if (originalCourse.isPresent()) {
            if (newTeacher.getFirstName() != null) {
                originalCourse.get().setTeacher(newTeacher);
            } else {
                originalCourse.get().setTeacher(null);
            }
            courseRepository.save(originalCourse.get());
        }
        return originalCourse.map(Course::getTeacher);
    }

    public Optional<Course> delete(int id) {
        Optional<Course> removeThisCourse = courseRepository.findById(id);
        if (removeThisCourse.isPresent()) {

//            Course tempCourse = removeThisCourse.get();
//            Hibernate.initialize(tempCourse.getStudents());
//            Hibernate.initialize(tempCourse.getTeacher());
//            Course copy =  new Course(tempCourse);
            courseRepository.deleteById(id);
            return removeThisCourse;
            //return Optional.of( copy);
        }
        return Optional.empty();
    }

    public Optional<Student> removeStudent(int id, int studentId) {
        Optional<Course> foundCourse = courseRepository.findById(id);
        if (foundCourse.isPresent()) {
            Course course = foundCourse.get();

            course.getStudents().removeIf(student -> student.getId() == studentId);
            courseRepository.save(course);
            return studentRepository.findById(studentId);
        }
        return Optional.empty();
    }
}
