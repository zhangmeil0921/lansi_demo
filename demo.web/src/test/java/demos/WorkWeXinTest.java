package demos;

import com.micro.service.driven.commons.json.gson.GsonBuilders;
import demo.start.Application;
import demo.web.serviceImpl.WorkWeXServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import response.AccessTokenResult;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 4:17 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WorkWeXinTest {

    @Autowired
    private WorkWeXServiceImpl workWeXService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getToken(){
        AccessTokenResult token = workWeXService.getToken();
        logger.info("企业微信获取的token--->{}", GsonBuilders.create().toJson(token));
    }
}
