package com.example.demo.entity.dto;

import com.example.demo.entity.Book;

import java.util.HashSet;
import java.util.Set;

public class BookDTO {
    int id;
    String name;
    int releaseYear;

    Set<UserDTO> userSet = new HashSet<>();

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.releaseYear = book.getReleaseYear();
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Set<UserDTO> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<UserDTO> userSet) {
        this.userSet = userSet;
    }
}
