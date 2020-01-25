package com.luanphm.dictionarybackend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DefinitionDetailDTO {
    private String definition;
    private String partOfSpeech;
    private List<String> typeOf;
    private List<String> partOf;
    private List<String> synonyms;
    private List<String> hasTypes;
    private List<String> verbGroup;
    @JsonAlias("derivation")
    private List<String> derivations;
    private List<String> examples;
}
