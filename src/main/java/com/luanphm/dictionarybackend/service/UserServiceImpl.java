package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.configuration.security.UserWithRoleDTO;
import com.luanphm.dictionarybackend.constant.ExceptionConstants;
import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.dto.UserChangeEmailDTO;
import com.luanphm.dictionarybackend.dto.UserChangePasswordDTO;
import com.luanphm.dictionarybackend.dto.UserInfoDTO;
import com.luanphm.dictionarybackend.dto.UserRegisterDTO;
import com.luanphm.dictionarybackend.entity.Role;
import com.luanphm.dictionarybackend.entity.User;
import com.luanphm.dictionarybackend.repository.user.UserRepository;
import com.luanphm.dictionarybackend.service.SharedService.MyAbstractSession;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends MyAbstractSession implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserWithRoleDTO getUserWithRole(String username) {
        User user = userRepository.getUserWithRole(username);
        UserWithRoleDTO dto = new UserWithRoleDTO(user);
        return dto;
    }

    @Override
    public boolean addUser(UserRegisterDTO dto) throws Exception {
        Session session = getSession();
        dto.setPassword(CommonUtilities.hashPassword(dto.getPassword()));

        userRepository.add(session, new User(dto.getUsername(), dto.getEmail(), dto.getPassword(), new Role(2)));

        return true;
    }

    @Override
    public boolean changePassword(UserChangePasswordDTO dto) throws Exception {
        if (dto == null
                || dto.getNewPassword() == null
                || dto.getNewPassword().isEmpty()
                || dto.getOldPassword() == null
                || dto.getOldPassword().isEmpty()
        ) {
            throw new Exception(ExceptionConstants.INPUT_ERROR);
        };

        User user = userRepository.getById(SecurityUtils.getCurrentUser());

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) throw new Exception("Wrong password. Please check it again");

        if (dto.getOldPassword().equals(dto.getNewPassword())) throw new Exception("Old password equals new password");

        String newHashedPassword = CommonUtilities.hashPassword(dto.getNewPassword());
        user.setPassword(newHashedPassword);

        userRepository.update(getSession(), user);
        return true;
    }

    @Override
    public boolean changeEmail(UserChangeEmailDTO dto) throws Exception {
        if (dto == null || dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new Exception(ExceptionConstants.INPUT_ERROR);
        }

        User user = userRepository.getById(SecurityUtils.getCurrentUser());

        if (user.getEmail().equals(dto.getEmail())) {
            throw new Exception("Old email equals new email");
        }

        user.setEmail(dto.getEmail());
        userRepository.update(getSession(), user);
        return true;
    }

    @Override
    public UserInfoDTO getUser() throws Exception {
        String currentUser = SecurityUtils.getCurrentUser();
        if (currentUser == null) return null;

        User user = userRepository.getById(currentUser);


        return UserInfoDTO.builder()
                .username(currentUser)
                .email(user.getEmail())
                .build();
    }
}
