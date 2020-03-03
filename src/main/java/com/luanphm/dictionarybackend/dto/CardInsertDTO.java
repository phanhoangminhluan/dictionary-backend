package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInsertDTO {

    private String term;

    private String definition;
}
