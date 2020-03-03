package com.luanphm.dictionarybackend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInsertManyDTO {

    private String cardSetId;
    private List<CardInsertDTO> cards;
}
