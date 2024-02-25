package kea.exercise.hogwarts_api.dtos;

import java.util.List;

public class StudentIdRequestDTO {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName(){
        return name.split(" ")[0];
    }
    public String getMiddleName(){
        String[] names = name.split(" ");
        if (names.length > 2) {
            return name.split(" ")[1];
        }
        return null;
    }
    public String getLastName(){
        String[] names = name.split(" ");
        return names[names.length-1];
    }
}
