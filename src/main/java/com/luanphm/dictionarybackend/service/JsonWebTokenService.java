package com.luanphm.dictionarybackend.service;

public interface JsonWebTokenService {

    void add(String username, String token);

    boolean isExisted(String key, String token);

    boolean deleteById(String key) throws Exception;

}
