package com.luanphm.dictionarybackend.configuration.security;

import com.luanphm.dictionarybackend.dto.RoleDTO;
import com.luanphm.dictionarybackend.entity.User;
import com.luanphm.dictionarybackend.utility.RecordNotFoundException;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWithRoleDTO {

    private String username;

    private String password;

    private String email;

    private RoleDTO roleDTO;

    public UserWithRoleDTO(User user) {
        if (user != null) {
            this.username = user.getId();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.roleDTO = new RoleDTO(user.getRole());
        } else {
            throw new RecordNotFoundException("USER NOT FOUND");
        }
    }

}
