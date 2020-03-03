package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudiableCardDTO {

    private StudiableCardIdDTO id;

    private boolean isRemember;

    private int rememberCount;

    private int forgetCount;
}
