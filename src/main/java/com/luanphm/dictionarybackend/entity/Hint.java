package com.luanphm.dictionarybackend.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "recommendation-words", type = "_doc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hint extends BaseEntity<String> {
    private String word;

    @Field("word_texts")
    private String wordText;

    @Field("word_search")
    private String wordSearch;
}
