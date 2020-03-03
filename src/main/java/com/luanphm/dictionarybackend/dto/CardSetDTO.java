package com.luanphm.dictionarybackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardSetDTO {

    private String id;

    private String name;

    private String username;

    private String createdDate;

    private List<CardDTO> cards;



}
