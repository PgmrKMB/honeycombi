package com.project.honeycombi.config;

import com.project.honeycombi.interceptor.SignInCheckInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    SignInCheckInterceptor signInCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        registry
        .addInterceptor(signInCheckInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/main","/sign","/signup","/signin","/images/**","/css/**","/js/**","/user/check",
        "/mania","/vegan/list", "/contact", "/haney/list");

        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
