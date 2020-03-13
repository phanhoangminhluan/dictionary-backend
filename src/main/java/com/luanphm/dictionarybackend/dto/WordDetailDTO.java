package com.luanphm.dictionarybackend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WordDetailDTO {

    private String word;
    private List<DefinitionDetailDTO> definitionDetails;
    private String ukPhonetic;
    private String usPhonetic;
}




