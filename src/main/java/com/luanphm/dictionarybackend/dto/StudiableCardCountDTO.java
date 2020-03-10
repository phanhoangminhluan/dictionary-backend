package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudiableCardCountDTO {
    private int rememberCount;
    private int forgetCount;
}
