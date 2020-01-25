package com.luanphm.dictionarybackend.entity.word_entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefinitionDetail {
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
