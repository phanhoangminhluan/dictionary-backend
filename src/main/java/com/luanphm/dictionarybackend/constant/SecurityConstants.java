package com.luanphm.dictionarybackend.constant;

public class SecurityConstants {

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
    };
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";


}