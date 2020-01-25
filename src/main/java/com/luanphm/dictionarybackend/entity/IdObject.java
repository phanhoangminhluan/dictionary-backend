package com.luanphm.dictionarybackend.entity;


import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdObject {
    @Id
    private String id;
}
