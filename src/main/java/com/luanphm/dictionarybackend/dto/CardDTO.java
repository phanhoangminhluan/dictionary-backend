package com.luanphm.dictionarybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private String id;

    private String term;

    private String definition;

    private int rememberCount;

    private int forgetCount;

    private String cardSetId;
}
