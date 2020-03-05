package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudiableCardDTO {

    private String cardId;

    private String cardSetSessionId;

    private boolean remember;

    private int rememberCount;

    private int forgetCount;
}
