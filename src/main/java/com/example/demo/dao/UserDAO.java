package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
    User findUserByLogin(String login);

    User findUserByEmail(String email);
}
