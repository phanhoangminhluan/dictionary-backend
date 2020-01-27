package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "main_user")
@Builder
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User (String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
