package com.example.demo.web.filter;

import com.example.demo.entity.UserSession;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static String LOGIN_REQUEST = "/users/login";
    private static String SAVE_REQUEST = "/users/save";

    private AuthenticationService authenticationService;

    public AuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String saveURI = req.getContextPath() + SAVE_REQUEST;
        boolean isSaveRequest = req.getRequestURI().equals(saveURI);

        String loginURI = req.getContextPath() + LOGIN_REQUEST;
        boolean isLoginRequest = req.getRequestURI().equals(loginURI);

        String sessionId = req.getHeader("sessionId");

        UserSession userSession = null;
        if (sessionId != null) {
            userSession = authenticationService.getSession(sessionId);
        }
        if (userSession != null || isLoginRequest || isSaveRequest) {
            resp.setHeader("sessionId", sessionId);
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect("users/login");
        }
    }

    @Override
    public void destroy() {

    }
}
