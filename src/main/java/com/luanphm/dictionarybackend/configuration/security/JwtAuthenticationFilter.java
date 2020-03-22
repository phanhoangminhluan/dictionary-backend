package com.luanphm.dictionarybackend.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanphm.dictionarybackend.service.JsonWebTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String SECRET = "NGUYEN DO TRA MY";
    public static final long EXPIRATION_TIME = 24 * 60 * 60 * 60; // 1 day
    private final String TOKEN_PREFIX = "Bearer ";
    private final String HEADER_STRING = "Authorization";

    private AuthenticationManager authenticationManager;
    private JsonWebTokenService jsonWebTokenService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JsonWebTokenService jsonWebTokenService) {
        this.authenticationManager = authenticationManager;
        this.jsonWebTokenService = jsonWebTokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLogin userLogin = null;

        try {
            userLogin = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetail userDetails = (CustomUserDetail) authResult.getPrincipal();
        String token  = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        jsonWebTokenService.add(userDetails.getUsername(), token);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-success");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-fail");
        requestDispatcher.forward(request, response);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class UserLogin {
    private String username;
    private String password;
}
