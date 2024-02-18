package kea.exercise.hogwarts_api.main;

import kea.exercise.hogwarts_api.models.*;
import kea.exercise.hogwarts_api.repositories.CourseRepository;
import kea.exercise.hogwarts_api.repositories.HouseRepository;
import kea.exercise.hogwarts_api.repositories.StudentRepository;
import kea.exercise.hogwarts_api.repositories.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    private CourseRepository courseRepo;
    private HouseRepository houseRepo;
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;

    public InitData(CourseRepository courseRepository, HouseRepository houseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        courseRepo = courseRepository;
        houseRepo = houseRepository;
        studentRepo = studentRepository;
        teacherRepo = teacherRepository;
    }

    public void run(String... args) {


        var gryffindor = houseRepo.save(new House("Gryffindor", "Godric Gryffindor", "Scarlet and gold")) ;
        var slytherin = houseRepo.save(new House("Slytherin", "Salazar Slytherin", "Green and silver"));
        var hufflepuff = houseRepo.save(new House("Hufflepuff", "Helga Hufflepuff", "Yellow and black"));
        var ravenclaw = houseRepo.save(new House("Ravenclaw", "Rowena Ravenclaw", "Blue and bronze"));


        var snape = teacherRepo.save(new Teacher("Severus", null, "Snape", LocalDate.now(), slytherin, true, EmpType.Tenured,
                LocalDate.parse("1981-05-01"), null)) ;

        var harry = studentRepo.save(new Student("Harry", "James", "Potter", LocalDate.now(), gryffindor,
                false, 1991, 1998, false)) ;
        var ron = studentRepo.save(new Student("Ronald", null, "Weasley", LocalDate.now(), gryffindor,
                false, 1991, 1998, false));
        var hermione =studentRepo.save(new Student("Hermione", null, "Granger", LocalDate.now(), gryffindor,
                false, 1991, 1998, false)) ;
        var hannah = studentRepo.save(new Student("Hannah", null, "Abott", LocalDate.now(), hufflepuff,
                false, 1991, 1998, false));
        var justin = studentRepo.save(new Student("Justin", null, "Finch-Fletchley", LocalDate.now(), hufflepuff,
                false, 1991, 1998, false));
        var megan = studentRepo.save(new Student("Megan", null, "Jones", LocalDate.now(), hufflepuff,
                false, 1991, 1998, false));
        var vincent = studentRepo.save(new Student("Vincent", null, "Crabbe", LocalDate.now(), slytherin,
                false, 1991, 1998, false));
        var draco = studentRepo.save(new Student("Draco", null, "Malfoy", LocalDate.now(), slytherin,
                false, 1991, 1998, false));
        var pansy = studentRepo.save(new Student("Pansy", null, "Parkinson", LocalDate.now(), slytherin,
                false, 1991, 1998, false));
        var terry = studentRepo.save(new Student("Terry", null, "Boot", LocalDate.now(), ravenclaw,
                false, 1991, 1998, false));
        var sue = studentRepo.save(new Student("Sue", null, "Li", LocalDate.now(), ravenclaw,
                false, 1991, 1998, false));
        var padma = studentRepo.save(new Student("Padma", null, "Patil", LocalDate.now(), ravenclaw,
                false, 1991, 1998, false));

        List<Student> studentsInCourse = Arrays.asList(harry, ron, hermione, hannah, justin, megan, vincent, draco, pansy, terry, sue);

        courseRepo.save(new Course("Potions", 1991, true, snape, studentsInCourse));
    }
}
