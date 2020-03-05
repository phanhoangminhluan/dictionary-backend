package com.luanphm.dictionarybackend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardSetUpdateNameDTO {

    private String id;

    private String name;
}
