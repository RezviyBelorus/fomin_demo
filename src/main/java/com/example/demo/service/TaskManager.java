package com.example.demo.service;

public class TaskManager implements Runnable {
    private AuthenticationService service;
    public TaskManager(AuthenticationService service) {
        this.service=service;
    }

    @Override
    public void run() {
        while (true) {
            service.clearExpiredSessions();
        }
    }
}
