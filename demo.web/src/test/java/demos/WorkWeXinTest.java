package demos;

import demo.start.Application;
import demo.web.serviceImpl.WorkWeXinTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 4:17 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WorkWeXinTest {

    private WorkWeXinTemplate workWeXinTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getToken(){
        String token = workWeXinTemplate.getAccessToken();
        logger.info("企业微信获取的token--->{}",token);
    }
}
