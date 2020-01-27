package com.luanphm.dictionarybackend.configuration.security;

import com.luanphm.dictionarybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWithRoleDTO dto = userService.getUserWithRole(username);
        if (dto == null) {
            throw new UsernameNotFoundException("USERNAME NOT FOUND");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(dto.getRoleDTO().getRoleName()));

        CustomUserDetail userDetail = new CustomUserDetail(dto.getUsername(), dto.getPassword(), authorities, dto.getEmail());
        return userDetail;
    }
}