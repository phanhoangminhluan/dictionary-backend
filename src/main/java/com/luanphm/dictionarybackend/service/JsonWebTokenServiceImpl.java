package com.luanphm.dictionarybackend.service;

import com.luanphm.dictionarybackend.configuration.security.JwtAuthenticationFilter;
import com.luanphm.dictionarybackend.constant.SecurityUtils;
import com.luanphm.dictionarybackend.utility.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class JsonWebTokenServiceImpl implements JsonWebTokenService {

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Override
    public void add(String username, String token) {
        String currentDate = CommonUtilities.getCurrentDateTimeForRedis();
        redisTemplate.opsForValue().set(username + " - " + currentDate, token);
        redisTemplate.expire(username, JwtAuthenticationFilter.EXPIRATION_TIME, TimeUnit.MILLISECONDS);
    }
    @Override
    public boolean isExisted(String username, String token) {
        if (username == null || username.isEmpty()) return false;
        String key = findById(username, token);
        return key != null;
    }

    @Override
    public boolean deleteById(String token) throws Exception {
        if (token == null || token.isEmpty()) return false;
        String username = SecurityUtils.getCurrentUser();
        String key = findById(username, token);
        return redisTemplate.delete(key);
    }

    private String findById(String username, String token) {
        Set<String> keys = redisTemplate.keys(username + "*");
        String tk = token.replace("Bearer ", "");
        String resultKey = null;
        for (String key : keys) {
            String redisToken = redisTemplate.opsForValue().get(key);
            if (redisToken.equals(tk)) {
                resultKey = key;
            }
        }
        return resultKey;
    }
}
