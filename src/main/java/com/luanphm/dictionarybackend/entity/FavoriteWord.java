package com.luanphm.dictionarybackend.entity;

import com.luanphm.dictionarybackend.constant.ElasticFields;
import com.luanphm.dictionarybackend.constant.ElasticIndexes;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName = ElasticIndexes.FAVORITE_WORD, type = "_doc")
public class FavoriteWord extends BaseEntity<String>{

    @Field(ElasticFields.WORD)
    private String word;

    @Field(ElasticFields.USER_ID)
    private String userId;

    @Field(ElasticFields.CREATED_DATE)
    private String createdDate;

    @Builder
    public FavoriteWord(String id, String userId, String word, String createdDate) {
        this.id = id;
        this.userId = userId;
        this.word = word;
        this.createdDate = createdDate;
    }

}
