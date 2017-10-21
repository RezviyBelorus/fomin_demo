package com.example.demo.service;

import com.example.demo.dao.UserSessionDAO;
import com.example.demo.entity.UserSession;
import com.example.demo.entity.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    UserSessionDAO userSessionDAO;

    @Value("${session.time.seconds}")
    private Long maxSessionTimeSeconds;

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public UserSession createSession(UserDTO userDTO) {
        UserSession userSession = new UserSession(userDTO);
        userSessionDAO.save(userSession);
        return new UserSession(userDTO);
    }

    public UserSession getSession(int userId) {
        UserSession userSession = userSessionDAO.findByUserId(userId);
        return userSession;
    }

    public UserSession getSession(String sessionId) {
        return userSessionDAO.findBySessionId(sessionId);
    }

    public void clearExpiredSessions() {
        Iterable<UserSession> all = userSessionDAO.findAll();
        if (all != null) {
            all.forEach(userSession -> {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime sessionDate = userSession.getSessionDate();
                long diff = Duration.between(sessionDate, now).getSeconds();
                if (diff > maxSessionTimeSeconds) {
                    userSessionDAO.delete(userSession);
                }
            });
        }
    }
}
