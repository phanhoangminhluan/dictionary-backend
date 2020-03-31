package com.luanphm.dictionarybackend.utility;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.luanphm.dictionarybackend.constant.CommonConstants;
import com.luanphm.dictionarybackend.entity.WordDetail;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtilities {

    public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date); //2016/11/16 12:08:43
    }

    public static String getCurrentDateTimeForRedis() {
        String dateTime = getCurrentDateTime();
        return dateTime.replace("/", "").replace(":", "").replace(" ", "");
    }


    public static Object parseJsonToObject(String json, Class clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj = null;
        try {
            obj = objectMapper.readValue(json, clazz);
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
