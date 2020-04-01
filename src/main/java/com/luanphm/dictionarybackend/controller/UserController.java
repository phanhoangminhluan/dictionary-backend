package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.*;
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
    public ResponseDTO register(@RequestBody UserRegisterDTO dto) throws Exception {
        userService.addUser(dto);
        return ResponseDTO.builder().isSuccess(true).body(dto).build();
    }

    @PutMapping("password")
    public ResponseEntity changePassword(@RequestBody UserChangePasswordDTO dto) throws Exception {
        boolean isChanged = userService.changePassword(dto);
        return isChanged
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @PutMapping("email")
    public ResponseEntity changeEmail(@RequestBody UserChangeEmailDTO dto) throws Exception {
        boolean isChanged = userService.changeEmail(dto);
        return isChanged
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

    @GetMapping()
    public ResponseEntity getUserInfor() throws Exception {
        UserInfoDTO dto = userService.getUser();
        return dto != null
                ? ResponseDTO.generateResponseObject(ResponseDTO.SUCCESS, ResponseDTO.RUN_SUCCESSFULLY, dto, HttpStatus.ACCEPTED)
                : ResponseDTO.generateResponseObject(ResponseDTO.FAIL, ResponseDTO.RUN_UNSUCCESSFULLY, ResponseDTO.EMPTY_BODY, HttpStatus.CONFLICT);
    }

}
