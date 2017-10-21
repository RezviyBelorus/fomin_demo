package com.example.demo.controller;

import com.example.demo.entity.dto.UserDTO;
import com.example.demo.service.AdminService;
import com.example.demo.web.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Aleksandr Fomin
 */
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * @param login User's login
     * @param role  User's role (all roles in Enum Role file)
     * @return ResponseEntity with Data Transfer Object if user with login exists in database and role is correct
     */
    @PatchMapping(path = "/role/set")
    public ResponseEntity setRole(HttpServletRequest req, HttpServletResponse resp,
                                  @RequestParam("login") String login, @RequestParam("role") String role) {
        boolean isAdmin = adminService.checkAccessLevel(req.getHeader("sessionId"));
        UserDTO userDTO;
        if (isAdmin) {
            userDTO = adminService.setUserRole(login, role);
        } else return new ResponseEntity(HttpStatus.FORBIDDEN);
        resp.setHeader("sessionId", req.getHeader("sessionId"));
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    /**
     * @param login User's login
     * @return ResponseEntity with Deleted user in DTO form
     */
    @DeleteMapping(path = "/user/delete")
    public ResponseEntity deleteUSer(HttpServletRequest req, HttpServletResponse resp,
                                     @RequestParam("login") String login) {
        boolean isAdmin = adminService.checkAccessLevel(req.getHeader("sessionId"));
        UserDTO userDTO;
        if (isAdmin) {
            userDTO = adminService.deleteByLogin(login);
        } else return new ResponseEntity(HttpStatus.FORBIDDEN);
        resp.setHeader("sessionId", req.getHeader("sessionId"));
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }
}
