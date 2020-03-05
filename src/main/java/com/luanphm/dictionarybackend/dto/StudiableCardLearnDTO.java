package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudiableCardLearnDTO {

    private String cardId;
    private String cardSetId;
    private String cardSetSessionId;

    private String term;
    private String definition;

    private String username;

    private boolean remember;

    private int rememberCount;

    private int forgetCount;

}
