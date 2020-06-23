package com.gnwoo.authservice.data.repo;

import com.gnwoo.authservice.data.repo.PasscodeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class PasscodeDAOImpl {
    @Autowired
    RedisTemplate<Long, String> redisTemplate;

//    private static final String KEY = "user";

    public Boolean savePasscode(Long uuid, String passcode) {
        try
        {
//            Map userHash = new ObjectMapper().convertValue(user, Map.class);
//            redisTemplate.opsForHash().put(KEY, user.getUuid(), userHash);
            redisTemplate.opsForValue().set(uuid, passcode, 10, TimeUnit.MINUTES);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public String findByUuid(Long uuid) {
//        Map userMap = (Map) redisTemplate.opsForHash().get(KEY, uuid);
//        User user = new ObjectMapper().convertValue(userMap, User.class);
        return redisTemplate.opsForValue().get(uuid);
    }

}