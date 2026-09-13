package com.example.eventpass.config;

import com.example.eventpass.interceptors.ControllerLoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomInterceptor implements WebMvcConfigurer {

    @Autowired
    private ControllerLoggingFilter controllerLoggingFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(controllerLoggingFilter)
                .addPathPatterns("/api/*");
    }
}
