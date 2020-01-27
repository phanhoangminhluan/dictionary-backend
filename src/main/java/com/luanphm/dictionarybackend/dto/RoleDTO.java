package com.luanphm.dictionarybackend.dto;

import com.luanphm.dictionarybackend.entity.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoleDTO {

    private int id;
    private String roleName;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
    }
}
