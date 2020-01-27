package com.luanphm.dictionarybackend.repository.word_detail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanphm.dictionarybackend.constant.ElasticIndexes;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.handler.ElasticSearchHandler;
import com.luanphm.dictionarybackend.handler.RapidApiRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "wordDetailElasticRepository")
public class WordDetailElasticRepositoryImpl extends ElasticSearchHandler<WordDetail> implements WordDetailRepository {
    @Autowired
    private RapidApiRequestHandler requestHandler;

    private ObjectMapper objectMapper = new ObjectMapper();

    public WordDetail getWord(String word) {
       return getById(word);
    }

    // need to refactor later
    public List<WordDetail> getByTerm(String term, String value) {
        Map<String, Object> termValuesMust = new HashMap<>();
        termValuesMust.put(term, value);
        List<WordDetail> wordDetails = getMany(termValuesMust, null, null);
        return wordDetails;
    }

    @Override
    public void saveWord(WordDetail wordDetail) {
        wordDetail.setId(wordDetail.getWord());
        save(wordDetail);
    }

    @Override
    public boolean updateWord(WordDetail wordDetail) throws JsonProcessingException {
        wordDetail.setId(wordDetail.getWord());
        return update(wordDetail);
    }

    @Override
    public boolean deleteWord(String id) {
        return deleteById(id);
    }

    @Override
    public boolean createWord(WordDetail wordDetail) {
        return create(wordDetail);
    }

    @Override
    public Class<WordDetail> getIndexClass() {
        return WordDetail.class;
    }

    @Override
    public String getIndexName() {
        return ElasticIndexes.WORD_DETAIL;
    }
}
