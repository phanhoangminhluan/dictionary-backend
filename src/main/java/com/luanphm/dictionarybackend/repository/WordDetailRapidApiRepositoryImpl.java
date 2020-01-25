package com.luanphm.dictionarybackend.repository;

import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.handler.RapidApiRequestHandler;
import com.luanphm.dictionarybackend.utility.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "wordDetailRapidApiRepository")
public class WordDetailRapidApiRepositoryImpl implements WordDetailRepository {

    @Autowired
    private RapidApiRequestHandler<WordDetail> rapidApiRequestHandler;

    @Override
    public WordDetail getWord(String word) {
        String wordJson = rapidApiRequestHandler.requestToWordsApi(word, Endpoint.WORDS).getBody();

        WordDetail wordDetail = rapidApiRequestHandler.parseJsonToObject(wordJson, WordDetail.class);

        return wordDetail;
    }

    @Override
    public List<WordDetail> getByTerm(String term, String value) {
        return null;
    }

    @Override
    public void saveWord(WordDetail wordDetail) {

    }
    @Override
    public boolean updateWord(WordDetail wordDetail) {

        return false;
    }
    @Override
    public boolean deleteWord(String id) {

        return false;
    }

    @Override
    public boolean createWord(WordDetail wordDetail) {
        return false;
    }

}
