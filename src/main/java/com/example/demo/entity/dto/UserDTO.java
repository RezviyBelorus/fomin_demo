package com.example.demo.entity.dto;

import com.example.demo.entity.User;
import com.example.demo.utils.Transformator;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    int id;
    String login;
    String email;
    String password;
    String role;
    String sessionId;


    Set<BookDTO> books = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.books = Transformator.transformToBookDTO(user.getBooks());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDTO> books) {
        this.books = books;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
