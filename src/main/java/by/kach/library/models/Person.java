package by.kach.library.models;

import java.util.List;

/**
 * Model
 */
public class Person {
    private int id;
    private String name;
    private int yearOfBirth;

    public Person(int id, String name, int yearOfBirth, Book book) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
