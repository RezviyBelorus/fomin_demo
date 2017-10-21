package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TaskManagerService {
    @Autowired
    AuthenticationService service;

    private TaskManager taskManager;

    @PostConstruct
    public void init() {
        taskManager = new TaskManager(service);
        new Thread(taskManager).start();
    }
}
