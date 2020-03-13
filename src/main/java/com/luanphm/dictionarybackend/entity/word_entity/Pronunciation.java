package com.luanphm.dictionarybackend.entity.word_entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pronunciation {
    private String ukPhonetic;
    private String usPhonetic;
}
