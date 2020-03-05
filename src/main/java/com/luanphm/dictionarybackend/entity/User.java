package com.luanphm.dictionarybackend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "main_user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity<String> {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Builder
    public User(String id, String email, String password, Role role) {
       this.id = id;
       this.email = email;
       this.password = password;
       this.role = role;
   }

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

}
