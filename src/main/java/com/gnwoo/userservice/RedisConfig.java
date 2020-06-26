package com.gnwoo.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.annotation.PreDestroy;

@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {
//    @Value("${spring.redis.host}")
//    String hostName;
//    @Value("${spring.redis.port}")
//    Integer port;

    @Autowired
    RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<Long, String> redisTemplate() {

        RedisTemplate<Long, String> template = new RedisTemplate<>();
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

        template.setConnectionFactory(factory);

//        template.setKeySerializer(stringSerializer);
//        template.setHashKeySerializer(stringSerializer);
//        template.setValueSerializer(jdkSerializationRedisSerializer);
//        template.setHashValueSerializer(jdkSerializationRedisSerializer);

        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();

        return template;

    }

    @PreDestroy
    public void cleanRedis() {
        factory.getConnection()
                .flushDb();
    }
}
