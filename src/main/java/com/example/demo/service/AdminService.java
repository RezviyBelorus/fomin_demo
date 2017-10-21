package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserSession;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.exception.IllegalRequestException;
import com.example.demo.web.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    public UserDTO setUserRole(String login, String role) {
        User userByLogin = userDAO.findUserByLogin(login);
        if (userByLogin == null) {
            throw new IllegalRequestException("User not found: " + login);
        }
        Role roleEnum = null;
        try {
            roleEnum = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            //NOP
        }
        if (roleEnum == null) {
            throw new IllegalRequestException("Role not found: " + role);
        }
        userByLogin.setRole(roleEnum.getName());
        return new UserDTO(userByLogin);
    }

    public UserDTO deleteByLogin(String login) {
        User user = userDAO.findUserByLogin(login);
        userDAO.delete(user);
        return new UserDTO(user);
    }

    public boolean checkAccessLevel(String sessionId) {
        UserSession session = authenticationService.getSession(sessionId);
        if (session == null) {
            throw new IllegalRequestException("User is not logged in");
        }
        UserDTO userDTO = userService.findById(session.getUserId());
        boolean isAdmin = false;
        if (userDTO != null) {
            isAdmin = userDTO.getRole().equals(Role.ADMIN.getName());
        }
        return isAdmin;
    }
}
