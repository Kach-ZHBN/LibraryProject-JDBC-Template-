package by.kach.library.models;

/**
 * Модель.
 */
public class Book {
    private int id;
    private String name;
    private String author;
    private int yearOfPublish;
    private int personId;

    public Book(int id, String name, String author, int yearOfPublish, int personId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublish = yearOfPublish;
        this.personId = personId;

    }

    public Book(){}

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
