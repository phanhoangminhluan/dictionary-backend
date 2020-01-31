package com.luanphm.dictionarybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardSetSessionDTO {

    private String id;

    private String createdDate;

    private boolean isOpen = true;

    private String cardSetId;
}
