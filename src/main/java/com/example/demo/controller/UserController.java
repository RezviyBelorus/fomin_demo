package com.example.demo.controller;

import com.example.demo.entity.dto.UserDTO;
import com.example.demo.service.UserService;
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
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * @return Response entity with Httpstatus.FORBIDDEN which means user should login
     */
    @GetMapping(path = "/login")
    public ResponseEntity login() {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     *
     * @param resp HttpServletResponse response
     * @param userDTO User in DTO form
     * @return Response entity with User in DTO form and Httpstatus if login and password for this user exists in database
     */
    @PostMapping(path = "/login")
    public ResponseEntity login(HttpServletResponse resp, @RequestBody UserDTO userDTO) {
        UserDTO loginedUserDTO = userService.login(userDTO.getLogin(), userDTO.getPassword());
        if (loginedUserDTO != null) {
            resp.setHeader("sessionId", loginedUserDTO.getSessionId());
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(loginedUserDTO, HttpStatus.OK);
    }

    /**
     *
     * @param userDTO potential to save User in DTO form
     * @return Response entity with User in DTO form and Httpstatus.OK if user was saved
     */
    @PostMapping(path = "/save")
    public ResponseEntity saveUser(@RequestBody UserDTO userDTO) {

        UserDTO savedUserDTO = userService.saveUser(userDTO);
        return new ResponseEntity(savedUserDTO, HttpStatus.OK);
    }

    /**
     *
     * @param login User's login
     * @return Response entity with User in DTO form and Httpstatus.OK if user exists in database
     */

    @GetMapping(path = "/login/{login}")
    public ResponseEntity findByLogin(@PathVariable(value = "login") String login) {
        UserDTO userDTO = userService.findByLogin(login);
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    /**
     *
     * @param email User's email
     * @return Response entity with User in DTO form and Httpstatus.OK if user exists in database
     */
    @GetMapping(path = "/email/{email}")
    public ResponseEntity findByEmail(@PathVariable(value = "email") String email) {
        UserDTO userDTO = userService.findByEmail(email);
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }
}
