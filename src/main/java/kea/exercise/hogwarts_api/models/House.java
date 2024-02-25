package kea.exercise.hogwarts_api.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class House {

    @Id
    private String name;
    private String founder;

    private String color1;
    private String color2;
    //private List<String> colors;

    public House(String name, String founder, String color1, String color2) {
        this.name = name;
        this.founder = founder;
        this.color1 = color1;
        this.color2 = color2;
    }
//    public House(String name, String founder, ArrayList<String> colors) {
//        this.name = name;
//        this.founder = founder;
//        this.colors = colors;
//    }

    public House() {
    }
//    public int getId() {
//        return id;
//    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", founder='" + founder + '\'' +
                ", colors=" + color1 + " " + color2 +
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

    public String[] getColors() {
        return new String[]{color1, color2};
    }

    public void setColor(String color1, String color2) {
        this.color1 = color1;
        this.color2 = color2;

    }
    public void setColors(ArrayList<String> colors) {
        this.color1 = colors.get(0);
        this.color1 = colors.get(1);
    }
}
