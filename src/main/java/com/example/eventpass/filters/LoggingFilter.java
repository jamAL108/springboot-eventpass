package com.example.eventpass.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Request received for :" + req.getRequestURI() + " " + req.getMethod());
        chain.doFilter(request,response);
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println("Request Served " + res.getStatus());
    }
}
