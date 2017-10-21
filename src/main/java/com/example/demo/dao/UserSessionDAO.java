package com.example.demo.dao;

import com.example.demo.entity.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionDAO extends CrudRepository<UserSession, Integer> {

    UserSession findByUserId(int id);

    UserSession findBySessionId(String id);
}
