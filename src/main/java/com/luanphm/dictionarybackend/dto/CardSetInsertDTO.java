package com.luanphm.dictionarybackend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardSetInsertDTO {

    private String name;

    private List<CardInsertDTO> cards;


}
