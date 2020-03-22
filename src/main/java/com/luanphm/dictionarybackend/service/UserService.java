package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.UserChangeEmailDTO;
import com.luanphm.dictionarybackend.dto.UserChangePasswordDTO;
import com.luanphm.dictionarybackend.dto.UserRegisterDTO;
import com.luanphm.dictionarybackend.configuration.security.UserWithRoleDTO;

public interface UserService {

    UserWithRoleDTO getUserWithRole(String username);

    boolean addUser(UserRegisterDTO user);

    boolean changePassword(UserChangePasswordDTO dto);

    boolean changeEmail(UserChangeEmailDTO dto);

}
