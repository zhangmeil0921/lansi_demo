package demo.common;

import com.micro.service.driven.wechat.mp.service.WeChatMpCacheAccessor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.concurrent.TimeUnit;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.lojoy.applet")
public class AppConfiguration {

    /**
     * Redis 消息监听器容器.
     *
     * @param redisConnectionFactory the redis connection factory
     * @return the redis message listener container
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }

    /**
     * 微信公众号缓存实现
     *
     * @param stringRedisTemplate
     * @return
     */
    @Bean
    public WeChatMpCacheAccessor weChatMpCacheAccessor(StringRedisTemplate stringRedisTemplate) {
        return new WeChatMpCacheAccessor() {
            @Override
            public void setCacheValue(String key, String value, Long expire, TimeUnit timeUnit) {
                stringRedisTemplate.opsForValue().set(key, value, expire, timeUnit);
            }

            @Override
            public String getCacheValue(String key) {
                return stringRedisTemplate.opsForValue().get(key);
            }

            @Override
            public Boolean removeCacheValue(String key) {
                return stringRedisTemplate.delete(key);
            }
        };
    }

    @Bean
    public CommonsMultipartResolver filterMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(10000);
        return resolver;
    }

}
