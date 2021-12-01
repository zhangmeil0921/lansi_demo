package demo.web.service.filter;


import demo.common.DeCount;
import demo.common.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 *  当redis 中的key过期时，触发一个事件。
 *  我们可以算好需要执行的时间间隔作为key失效时间，这样就可以保证到点执行逻辑了。
 */
@Component
public class RedisEventMessageListener extends KeyExpirationEventMessageListener {
    /**
     * Instantiates a new Redis event message listener.
     *
     * @param listenerContainer the listener container
     */
    @Resource
    private  StringRedisTemplate stringRedisTemplate;

    public RedisEventMessageListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //获取到某个Redis的key
        String key = message.toString();
        System.out.println(key);
        //判断key是否是当前活动的key
        if("activityName".equals(key)){
            //获取当前时间用于判断当前活动还剩多少个
            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime now = LocalDateTime.of(2021,5,27,16,58,2);
            Integer remainCount = DeCount.getInteger(now);
            logger.info("当前剩余名额为："+remainCount+"");
            //获取当前时间距离下个小时还剩下的秒数
           Long between =  TimeUtils.extracted(now,1);
           logger.info("距离下一个整点秒数为："+between);
           stringRedisTemplate.opsForValue().set("activityName","",between, TimeUnit.SECONDS);
           stringRedisTemplate.opsForValue().set("remainCount",remainCount+"");
        }
    }
}