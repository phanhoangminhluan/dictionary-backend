package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.dto.UserRegisterDTO;
import com.luanphm.dictionarybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseDTO register(@RequestBody UserRegisterDTO dto) {
        userService.addUser(dto);
        return ResponseDTO.builder().isSuccess(true).body(dto).build();
    }

}
