package demo.web.serviceImpl;


import com.micro.service.driven.commons.json.gson.GsonBuilders;
import demo.common.DeCount;
import demo.common.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final StringRedisTemplate stringRedisTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner Start");
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime now = LocalDateTime.of(2021,5,27,15,59,0);
        logger.info("当前时间为"+ GsonBuilders.create().toJson(now));
        stringRedisTemplate.opsForValue().set("activityName", "来了", TimeUtils.extracted(now,1), TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set("remainCount", DeCount.getInteger(now)+"");
        System.out.println("redis successful");
    }
}
