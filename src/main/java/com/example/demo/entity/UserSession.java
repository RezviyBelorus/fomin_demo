package com.example.demo.entity;

import com.example.demo.entity.dto.UserDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_sessions")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int sessionNumber;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "session_date")
    private LocalDateTime sessionDate;

    public UserSession() {
    }

    public UserSession(UserDTO userDTO) {
        this.userId = userDTO.getId();
        this.sessionId = userDTO.getSessionId().toString();
        sessionDate = LocalDateTime.now();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int useId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSession that = (UserSession) o;

        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }
}
