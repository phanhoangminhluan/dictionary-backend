package com.luanphm.dictionarybackend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.luanphm.dictionarybackend.constant.ElasticFields;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DefinitionDetailDTO {
    private String definition;

    private String partOfSpeech;

    private List<String> examples;

    private List<String> verbGroup;

    @JsonAlias("derivation")
    private List<String> derivations;

    private List<String> synonyms;

    private List<String> antonyms;

    private List<String> typeOf;

    private List<String> hasTypes;

    private List<String> partOf;

    private List<String> hasParts;

    private List<String> instanceOf;

    private List<String> hasInstances;

    private List<String> similarTo;

    private List<String> also;

    private List<String> entails;

    private List<String> memberOf;

    private List<String> hasMembers;

    private List<String> substanceOf;

    private List<String> hasSubstances;

    private List<String> inCategory;

    private List<String> hasCategories;

    private List<String> usageOf;

    private List<String> inRegion;

    private List<String> regionOf;

    private List<String> pertainsTo;

    @JsonAlias("attribute")
    private List<String> attributes;
}
