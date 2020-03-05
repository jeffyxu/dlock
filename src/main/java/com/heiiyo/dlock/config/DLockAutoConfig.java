package com.heiiyo.dlock.config;

import com.heiiyo.dlock.lock.Lock;
import com.heiiyo.dlock.lock.impl.RedisLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisTemplate.class)
@EnableConfigurationProperties(DLockProperties.class)
@Import({ SpringJpaAutoConfig.class, SpringRedisAutoConfig.class})
public class DLockAutoConfig {

    @Bean
    public Lock lock(RedisTemplate redisTemplate, DLockProperties dLockProperties){
        Lock lock = null;
        switch (dLockProperties.getLockType()){
            case "redis":
                lock = new RedisLock(redisTemplate);
                break;
            case "zookeeper":
                break;

        }
        return  lock;
    }
}
