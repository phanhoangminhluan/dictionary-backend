package com.luanphm.dictionarybackend.configuration.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class CustomUserDetail extends User implements UserDetails, Serializable {

    private String email;


    public CustomUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities, String email) {
        super(username, password, authorities);
        this.email = email;
    }
}
