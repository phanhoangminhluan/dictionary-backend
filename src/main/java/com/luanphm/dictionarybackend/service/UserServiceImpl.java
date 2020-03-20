package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.UserRegisterDTO;
import com.luanphm.dictionarybackend.configuration.security.UserWithRoleDTO;
import com.luanphm.dictionarybackend.entity.Role;
import com.luanphm.dictionarybackend.entity.User;
import com.luanphm.dictionarybackend.repository.user.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserWithRoleDTO getUserWithRole(String username) {
        User user = userRepository.getUserWithRole(username);
        UserWithRoleDTO dto = new UserWithRoleDTO(user);
        return dto;
    }

    @Override
    public void addUser(UserRegisterDTO dto) {
        Session session = entityManager.unwrap(Session.class);
        dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)));
        userRepository.add(session, new User(dto.getUsername(), dto.getEmail(), dto.getPassword(), new Role(2)));
    }
}
