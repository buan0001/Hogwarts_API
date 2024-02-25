package kea.exercise.hogwarts_api.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   private String subject;
   private int schoolYear;
   private boolean current;

    @ManyToOne(fetch = FetchType.EAGER)
   private Teacher teacher;

   @ManyToMany(fetch = FetchType.EAGER)
   private Set<Student> students;

    public Course(String subject, int schoolYear, boolean current, Teacher teacher, Set<Student> students) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
        this.teacher = teacher;
        this.students = students;
    }

    public Course(Course other) {
        id = other.getId();
        subject = other.getSubject();
        schoolYear = other.getSchoolYear();
        current = other.isCurrent();
        teacher = other.getTeacher();
        students = other.getStudents();
    }

    public Course() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
