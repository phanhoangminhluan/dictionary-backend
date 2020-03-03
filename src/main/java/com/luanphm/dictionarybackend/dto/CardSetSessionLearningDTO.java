package com.luanphm.dictionarybackend.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardSetSessionLearningDTO {

    private String cardSetSessionId;

    private String cardSetId;

    private List<StudiableCardDTO> studiableCards;

}
