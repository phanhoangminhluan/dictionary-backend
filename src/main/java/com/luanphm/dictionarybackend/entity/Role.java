package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity<Integer> {

    @Column(name = "role_name")
    private String roleName;


    public Role(int roleId) {
        this.id = roleId;
    }
}


