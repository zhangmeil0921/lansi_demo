package demo.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设计师案例接口
 * @author Zml
 * @createDate 2021-07-05
 */
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("api/designer/case")
public class DesignerCaseController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("count")
    public String getCount(){
        Long increment = redisTemplate.opsForValue().increment("demo-count");
        return  "共有【"+increment+"】人访问了！";
    }
}
