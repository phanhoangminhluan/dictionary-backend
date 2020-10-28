package com.luanphm.dictionarybackend.constant;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static final String[] PUBLIC_ENDPOINTS = {
            "/login",
            "/user/register",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html/",
            "/swagger-ui.html/**",
            "/webjars/springfox-swagger-ui/**",
            "/v2/api-docs",
            "/",
            "/csrf",
            "/word/*",
            "/hint/*",
            "/authorize-fail",
            "/home",
            "/home/**"
    };
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final String getCurrentUser() throws Exception {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUserName == null) {
            throw new Exception("Username is not found");
        }
        return currentUserName;
    }
}