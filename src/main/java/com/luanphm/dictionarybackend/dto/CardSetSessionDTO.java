package com.luanphm.dictionarybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardSetSessionDTO {

    private String id;

    private String createdDate;

    private String cardSetId;

    private List<StudiableCardDTO> studiableCards;
}
