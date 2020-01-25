package com.luanphm.dictionarybackend.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.luanphm.dictionarybackend.constant.DFConstant;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

@Component
public class RapidApiRequestHandler<T> {

    public HttpResponse<String> requestToWordsApi(String word, String endpoint) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get(DFConstant.WORD_API_URL + word + DFConstant.FORWARD_SLASH + endpoint)
                    .header(DFConstant.RAPID_API_HOST_KEY, DFConstant.RAPID_API_HOST_VALUE)
                    .header(DFConstant.RAPID_API_KEY_KEY, DFConstant.RAPID_API_KEY_VALUE)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response;
    }

    public T parseJsonToObject(String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T obj = null;
        try {
            obj = objectMapper.readValue(json, clazz);
        } catch (UnrecognizedPropertyException e) {
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
