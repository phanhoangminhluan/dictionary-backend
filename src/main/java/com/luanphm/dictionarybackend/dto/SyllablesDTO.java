package com.luanphm.dictionarybackend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class SyllablesDTO {
    private int count;
    @JsonAlias("list")
    private List<String> syllableList;
}
