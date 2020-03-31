package com.luanphm.dictionarybackend.entity.word_entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.luanphm.dictionarybackend.constant.ElasticFields;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefinitionDetail {
    @Field(ElasticFields.DEFINITION)
    private String definition;

    @Field(ElasticFields.PART_OF_SPEECH)
    private String partOfSpeech;

    @Field(ElasticFields.LEVEL)
    private String level;

    @Field(ElasticFields.EXAMPLES)
    private List<String> examples;

    @Field(ElasticFields.DERIVATIONS)
    private List<String> derivations;

    @Field(ElasticFields.SYNONYMS)
    private List<String> synonyms;
}
