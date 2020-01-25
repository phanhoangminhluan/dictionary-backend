package com.luanphm.dictionarybackend.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;
import com.luanphm.dictionarybackend.entity.word_entity.DefinitionDetail;
import com.luanphm.dictionarybackend.entity.word_entity.Pronunciation;
import com.luanphm.dictionarybackend.entity.word_entity.Syllables;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "word-detail", type = "_doc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordDetail extends IdObject {
    private String word;
    @JsonAlias("results")
    private List<DefinitionDetail> definitionDetails = new ArrayList<>();
    private Syllables syllables;
    private Pronunciation pronunciation;
    private int frequency;

    public WordDetail(WordDetailDTO dto) {
        this.word = dto.getWord();
        this.syllables = Syllables.builder()
                    .count(dto.getSyllableList().size())
                    .syllableList(dto.getSyllableList())
                .build();
        this.pronunciation = Pronunciation.builder()
                    .all(dto.getPronunciation())
                .build();
        dto.getDefinitionDetails().forEach(detail -> {
            this.definitionDetails.add(DefinitionDetail.builder()
                        .definition(detail.getDefinition())
                        .derivations(detail.getDerivations())
                        .examples(detail.getExamples())
                        .hasTypes(detail.getHasTypes())
                        .partOf(detail.getPartOf())
                        .partOfSpeech(detail.getPartOfSpeech())
                        .synonyms(detail.getSynonyms())
                        .typeOf(detail.getTypeOf())
                        .verbGroup(detail.getVerbGroup())
                    .build());
        });
    }
}
