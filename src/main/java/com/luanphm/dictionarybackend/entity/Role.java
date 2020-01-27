package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    private int id;

    @Column(name = "role_name")
    private String roleName;


    public Role(int roleId) {
        this.id = roleId;
    }
}
