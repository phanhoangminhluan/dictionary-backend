package com.luanphm.dictionarybackend.entity.word_entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Syllables {
    private int count;
    @JsonAlias("list")
    private List<String> syllableList;
}
