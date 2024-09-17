package org.web.libraryms.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String number;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Book> books; // Список книг

    public People() {
    }

    public People(String name, String surname, String number, List<Book> books) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.books = books;
    }

    // Getters и Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
