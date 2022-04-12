package com.project.honeycombi.config;

import com.project.honeycombi.interceptor.SignInCheckInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    SignInCheckInterceptor signInCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        registry
        .addInterceptor(signInCheckInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/main","/sign","/signup","/signin","/css/**","/js/**","/user/check");

        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
