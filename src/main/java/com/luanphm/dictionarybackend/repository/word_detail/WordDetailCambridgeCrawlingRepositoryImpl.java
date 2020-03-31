package com.luanphm.dictionarybackend.repository.word_detail;

import com.luanphm.dictionarybackend.constant.CommonConstants;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "wordDetailCambridgeCrawlingRepository")
public class WordDetailCambridgeCrawlingRepositoryImpl implements WordDetailRepository {

    @Value("${crawler.url}")
    private String crawlerUrl;

    @Override
    public  WordDetail getWord(String word) {
        HttpResponse<String> response;
        try {
            response = Unirest.get(crawlerUrl + word).asString();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
        WordDetail wordDetail = (WordDetail) CommonUtilities.parseJsonToObject(response.getBody(), WordDetail.class);
        if (wordDetail == null) return null;
        wordDetail.setId(word);
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
