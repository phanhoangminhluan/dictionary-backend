package com.luanphm.dictionarybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudiableCardLearnDTO {

    private StudiableCardIdLearnDTO id;

    private boolean remember;

    private int rememberCount;

    private int forgetCount;

}
