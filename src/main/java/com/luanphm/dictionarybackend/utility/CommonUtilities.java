package com.luanphm.dictionarybackend.utility;


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

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
