package com.atlantico.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class RedisConfig {

    public static final String REDIS_CACHE_NAME="redis_cache_name";
    public static final String REDIS_PREFIX ="redis_cache_prefix";
    public static final Long EXPIRE =60*60L;

    
    //@Bean
    //public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
    //    return new RedisTokenStore(redisConnectionFactory);
    //}
    
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
    
}	