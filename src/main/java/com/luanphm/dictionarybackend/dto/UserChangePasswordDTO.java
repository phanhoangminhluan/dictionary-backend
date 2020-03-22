package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordDTO {
    private String password;
}
