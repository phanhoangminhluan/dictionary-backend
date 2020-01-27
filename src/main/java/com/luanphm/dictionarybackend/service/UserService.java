package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.dto.UserRegisterDTO;
import com.luanphm.dictionarybackend.configuration.security.UserWithRoleDTO;

public interface UserService {

    UserWithRoleDTO getUserWithRole(String username);

    void addUser(UserRegisterDTO user);

}
