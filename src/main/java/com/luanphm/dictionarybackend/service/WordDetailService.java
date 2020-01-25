package com.luanphm.dictionarybackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luanphm.dictionarybackend.dto.WordDetailDTO;

import java.util.List;

public interface WordDetailService {

    WordDetailDTO getWord(String word);

    List<WordDetailDTO> getByTerm(String term, String value);

    boolean update(WordDetailDTO wordDetail) throws JsonProcessingException;

    boolean delete(String id);

    boolean create(WordDetailDTO wordDetailDTO);
}
