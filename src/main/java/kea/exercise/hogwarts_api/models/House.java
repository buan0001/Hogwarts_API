package kea.exercise.hogwarts_api.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class House {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String founder;

    private String color;
    //private List<String> colors;

    public House(String name, String founder, String color) {
        this.name = name;
        this.founder = founder;
        this.color = color;
    }
//    public House(String name, String founder, ArrayList<String> colors) {
//        this.name = name;
//        this.founder = founder;
//        this.colors = colors;
//    }

    public House() {
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", founder='" + founder + '\'' +
                ", colors=" + color +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
//    public ArrayList<String> getColors() {
//        return colors;
//    }
//
//    public void setColors(ArrayList<String> colors) {
//        this.colors = colors;
//    }
}
