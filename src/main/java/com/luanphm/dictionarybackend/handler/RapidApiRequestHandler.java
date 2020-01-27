package com.luanphm.dictionarybackend.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.luanphm.dictionarybackend.constant.CommonConstants;
import com.luanphm.dictionarybackend.entity.RapidApiError;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

@Component
public class RapidApiRequestHandler<T> {

    public HttpResponse<String> requestToWordsApi(String word, String endpoint) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get(CommonConstants.WORD_API_URL + word + CommonConstants.FORWARD_SLASH + endpoint)
                    .header(CommonConstants.RAPID_API_HOST_KEY, CommonConstants.RAPID_API_HOST_VALUE)
                    .header(CommonConstants.RAPID_API_KEY_KEY, CommonConstants.RAPID_API_KEY_VALUE)
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
            RapidApiError rapidApiError = null;
            try {
                    rapidApiError = objectMapper.readValue(json, RapidApiError.class);
            } catch (JsonProcessingException ex) {
//                ex.printStackTrace();
            }
            if (rapidApiError == null) {
                e.printStackTrace();
            }
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
