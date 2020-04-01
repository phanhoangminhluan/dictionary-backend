package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.configuration.security.UserWithRoleDTO;
import com.luanphm.dictionarybackend.dto.UserChangeEmailDTO;
import com.luanphm.dictionarybackend.dto.UserChangePasswordDTO;
import com.luanphm.dictionarybackend.dto.UserInfoDTO;
import com.luanphm.dictionarybackend.dto.UserRegisterDTO;

public interface UserService {

    UserWithRoleDTO getUserWithRole(String username);

    boolean addUser(UserRegisterDTO user) throws Exception;

    boolean changePassword(UserChangePasswordDTO dto) throws Exception;

    boolean changeEmail(UserChangeEmailDTO dto) throws Exception;

    UserInfoDTO getUser() throws Exception;

}
