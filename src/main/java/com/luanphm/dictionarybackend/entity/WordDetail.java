package com.luanphm.dictionarybackend.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.luanphm.dictionarybackend.constant.ElasticFields;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.entity.word_entity.Pronunciation;
import com.luanphm.dictionarybackend.handler.DefinitionDetailMapping;
import lombok.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "word-detail", type = "_doc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordDetail extends BaseEntity<String> {
    private String word;
    @Field(ElasticFields.DEFINITION_DETAIL)
    private List<DefinitionDetail> definitionDetails = new ArrayList<>();

    private Pronunciation pronunciation;

    public WordDetail(WordDetailDTO dto) {
        DefinitionDetailMapping mappingHandler = Mappers.getMapper(DefinitionDetailMapping.class);
        this.word = dto.getWord();

        this.pronunciation = Pronunciation.builder()
                    .ukPhonetic(dto.getUkPhonetic())
                    .usPhonetic(dto.getUsPhonetic())
                .build();
        dto.getDefinitionDetails().forEach(detail -> {
            this.definitionDetails.add(mappingHandler.dtoToDefinitionDetail(detail));
        });
    }
}