package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.dto.UserChangeEmailDTO;
import com.luanphm.dictionarybackend.dto.UserChangePasswordDTO;
import com.luanphm.dictionarybackend.dto.UserRegisterDTO;
import com.luanphm.dictionarybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("password")
    public ResponseEntity changePassword(@RequestBody UserChangePasswordDTO dto) {
        boolean isChanged = userService.changePassword(dto);
        return isChanged
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping("email")
    public ResponseEntity changeEmail(@RequestBody UserChangeEmailDTO dto) {
        boolean isChanged = userService.changeEmail(dto);
        return isChanged
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

}
