package com.luanphm.dictionarybackend.configuration.security;

import com.luanphm.dictionarybackend.service.JsonWebTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final String SECRET = "NGUYEN DO TRA MY";
    private final String TOKEN_PREFIX = "Bearer ";
    private final String HEADER_STRING = "Authorization";

    private UserDetailsService userDetailsService;

    private JsonWebTokenService jsonWebTokenService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JsonWebTokenService jsonWebTokenService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.jsonWebTokenService = jsonWebTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/token-required");
//            requestDispatcher.forward(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        if (authenticationToken == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/authorize-fail");
            requestDispatcher.forward(request, response);
        } else {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }
    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token == null) {return null;}
        String username = null;
        try {
             username = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return null;
        } catch (Exception e) {
            return null;
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean isExisted = jsonWebTokenService.isExisted(username, token);

        if (!isExisted) return null;

        return username != null ? new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
    }
}
