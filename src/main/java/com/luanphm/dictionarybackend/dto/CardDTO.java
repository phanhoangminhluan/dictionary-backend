package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private String id;

    private String term;

    private String definition;

    private String cardSetId;
}
