package com.luanphm.dictionarybackend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.entity.word_entity.Pronunciation;
import com.luanphm.dictionarybackend.entity.word_entity.Syllables;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class WordDetailDTO {

    private String word;
    private String pronunciation;
    private int frequency;
    private List<String> syllableList;
    private List<DefinitionDetailDTO> definitionDetails;

    public static class WordDetailDTOBuilder {

        private List<DefinitionDetailDTO> definitionDetails = new ArrayList<>();

        public WordDetailDTOBuilder definitionDetails(List<DefinitionDetail> definitionDetail) {
            definitionDetail.forEach( detail -> {
                this.definitionDetails.add(
                        DefinitionDetailDTO.builder()
                                .definition(detail.getDefinition())
                                .examples(detail.getExamples())
                                .derivations(detail.getDerivations())
                                .hasTypes(detail.getHasTypes())
                                .partOf(detail.getPartOf())
                                .partOfSpeech(detail.getPartOfSpeech())
                                .synonyms(detail.getSynonyms())
                                .typeOf(detail.getTypeOf())
                                .verbGroup(detail.getVerbGroup())
                                .build()
                );

            });
            return this;
        }
    }
}




