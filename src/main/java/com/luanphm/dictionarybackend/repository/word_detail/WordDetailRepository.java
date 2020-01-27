package com.luanphm.dictionarybackend.repository.word_detail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.luanphm.dictionarybackend.entity.WordDetail;

import java.util.List;

public interface WordDetailRepository {

    WordDetail getWord(String word);

    List<WordDetail> getByTerm(String term, String value);

    void saveWord(WordDetail wordDetail);

    boolean updateWord(WordDetail wordDetail) throws JsonProcessingException;

    boolean deleteWord(String id);

    boolean createWord(WordDetail wordDetail);

}
