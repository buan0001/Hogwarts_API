package kea.exercise.hogwarts_api.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;



    //private String house;
    @ManyToOne
    private House house;
    private boolean prefect;
    private int enrollmentYear;
    private int graduationYear;
    private boolean graduated;

    public Student() {
    }
    //House house,
    public Student(String firstName,
                   String middleName,
                   String lastName,
                   LocalDate dateOfBirth,
                   House house,

                   boolean prefect,
                   int enrollmentYear,
                   int graduationYear,
                   boolean graduated) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.prefect = prefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.graduated = graduated;
    }

    public Student(Student other) {
        firstName = other.firstName;
        middleName = other.middleName;
        lastName = other.getLastName();
        dateOfBirth = other.getDateOfBirth();
        house = other.getHouse();
        prefect = other.isPrefect();
        enrollmentYear = other.getEnrollmentYear();
        graduationYear = other.getGraduationYear();
        graduated = other.isGraduated();
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    public String getHouse() {
//        return house;
//    }
//
//    public void setHouse(String house) {
//        this.house = house;
//    }
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public boolean isPrefect() {
        return prefect;
    }

    public void setPrefect(boolean prefect) {
        this.prefect = prefect;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }
}
