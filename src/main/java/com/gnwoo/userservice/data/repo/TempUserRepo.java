package com.gnwoo.userservice.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Repository
public class TempUserRepo {
    @Autowired
    @Qualifier("RedisStringTemplate")
    RedisTemplate<String, String> redisTemplate;

    public void saveTempUser(String username, String hashed_password, String display_name, String email,
                             boolean is2FA, String passcode) {
        try {
            HashMap<String, Object> testMap = new HashMap<>();
            testMap.put("username", username);
            testMap.put("hashed_password", hashed_password);
            testMap.put("display_name", display_name);
            testMap.put("is2FA", is2FA);
            testMap.put("passcode", passcode);

            redisTemplate.opsForHash().putAll(email, testMap);
            redisTemplate.expire(email, 10, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Object, Object> findTempUserByEmail(String email) {
        return redisTemplate.opsForHash().entries(email);
    }

    public void deleteTempUserByEmail(String email) {
        redisTemplate.delete(email);
    }

}
