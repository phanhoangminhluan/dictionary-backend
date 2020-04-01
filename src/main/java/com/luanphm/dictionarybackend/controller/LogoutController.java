package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import com.luanphm.dictionarybackend.service.JsonWebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @GetMapping("logout-handler")
    public ResponseEntity logoutHandler(@RequestHeader("Authorization") String authorization) throws Exception {
        jsonWebTokenService.deleteById(authorization);
        return ResponseDTO.generateResponseObject(true, ResponseDTO.RUN_SUCCESSFULLY, null, HttpStatus.OK);
    }

    @GetMapping("logout")
    public String logout() {
        return "";
    }
}
