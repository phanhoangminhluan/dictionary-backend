package com.luanphm.dictionarybackend.controller;

import com.luanphm.dictionarybackend.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @PostMapping("login-success")
    public ResponseEntity login(HttpServletResponse response) {
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        class AuthenticationInformation {
            String token;
        }
        return ResponseDTO.generateResponseObject(true, "Login success", new AuthenticationInformation(response.getHeader("Authorization")), HttpStatus.OK);
    }

    @PostMapping("login-fail")
    public ResponseEntity loginFail() {
        return ResponseDTO.generateResponseObject(false, "Failed to login.", ResponseDTO.EMPTY_BODY, HttpStatus.UNAUTHORIZED);
    }

}
