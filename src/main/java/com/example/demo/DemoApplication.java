package com.example.demo;

import com.example.demo.service.AuthenticationService;
import com.example.demo.web.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    @Autowired
    public FilterRegistrationBean authenticationFilter(AuthenticationService authenticationService) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter(authenticationService));
        registration.addUrlPatterns("/*");
        registration.setName("AuthenticationFilter");

        return registration;
    }
}
