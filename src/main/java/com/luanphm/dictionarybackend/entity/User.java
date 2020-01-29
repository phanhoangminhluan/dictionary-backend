package com.luanphm.dictionarybackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "main_user")
@Getter
@Setter
@NoArgsConstructor
public class User extends IdObject<String> {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

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
        this.role = role;
    }

}
