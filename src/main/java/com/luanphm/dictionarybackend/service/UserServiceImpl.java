package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.UserChangeEmailDTO;
import com.luanphm.dictionarybackend.dto.UserChangePasswordDTO;
import com.luanphm.dictionarybackend.dto.UserRegisterDTO;
import com.luanphm.dictionarybackend.configuration.security.UserWithRoleDTO;
import com.luanphm.dictionarybackend.entity.Role;
import com.luanphm.dictionarybackend.entity.User;
import com.luanphm.dictionarybackend.repository.user.UserRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class UserServiceImpl extends MyAbstractSession implements UserService {

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
    public boolean addUser(UserRegisterDTO dto) {
        Session session = getSession();
        dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)));
        return userRepository.add(session, new User(dto.getUsername(), dto.getEmail(), dto.getPassword(), new Role(2)));
    }

    @Override
    public boolean changePassword(UserChangePasswordDTO dto) {
        if (dto == null || dto.getPassword() == null || dto.getPassword().isEmpty()) return false;

        User user = userRepository.getById(SecurityUtils.getCurrentUser());
        dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)));
        if (user == null || user.getPassword().equals(dto.getPassword())) return false;
        user.setPassword(dto.getPassword());
        return userRepository.update(getSession(), user);

    }

    @Override
    public boolean changeEmail(UserChangeEmailDTO dto) {
        if (dto == null || dto.getEmail() == null || dto.getEmail().isEmpty()) return false;

        User user = userRepository.getById(SecurityUtils.getCurrentUser());
        if (user == null || user.getEmail().equals(dto.getEmail())) return false;
        user.setEmail(dto.getEmail());
        return userRepository.update(getSession(), user);
    }


}
