package demos;

import com.micro.service.driven.commons.entity.ItemResp;
import com.micro.service.driven.commons.json.gson.GsonBuilders;
import com.micro.service.driven.commons.utils.DateUtils;
import com.micro.service.driven.wechat.mp.entity.response.PermanentNewsMaterialListResp;
import demo.common.CommonUtil;
import demo.common.Material;
import demo.common.Token;
import config.WeChatConfig;
import demo.start.Application;
import demo.web.service.MaterialSourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author Zml
 * @createDate 2021-07-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MaterialTest {

    @Autowired
    MaterialSourceService materialSourceService;


    @Test
    public void testGetMaterial() {
//        Token token = CommonUtil.getToken(WeChatConfig.APP_ID,WeChatConfig.APP_SECRET);//获取接口访问凭证access_token
        String accessToken = "47_jFgO9a0m7jPYU-F9BRhNQCTJVdKDJrjPA9Cg1FG3KIJ9rFUzpl5t7fDoHtR-Y7nzq6O0UgAzvpaN6XnkZ5uklex2i9Vw3ETRhoq71YfZqVwRvYpBFnjxe0O1LxmiKt-1bEx7jspPC0kKpDSCYFMgAJABIP";
        List<Material> lists = CommonUtil.getMaterial(accessToken, "news", 0, 10);//调用获取素材列表的方法
        System.out.println(lists.size());//输出
    }

    @Test
    public void testGetToken2() {
        Token token = CommonUtil.getToken(WeChatConfig.APP_ID_GZ, WeChatConfig.APP_SECRET_GZ);//其中的WeChatConfig方法中有定义的自己微信公众号的appid和appsecret值 用到时直接调用
        System.out.println("access_token:" + token.getAccessToken());
        System.out.println("expires_in:" + token.getExpiresIn());
    }

    @Test
    public void testMaterialSource() {
        ItemResp<PermanentNewsMaterialListResp> materialSource = materialSourceService.getMaterialSource();
        System.out.println("result->"+ GsonBuilders.create().toJson(materialSource));
    }

    @Test
    public void dateTest(){
        long l = System.currentTimeMillis();
        Long intValue = 1627545090000l ;
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(intValue), zone);
        long l1 = System.currentTimeMillis();
        LocalDateTime createDate = DateUtils.timeStampToDate(intValue);
        long l2 = System.currentTimeMillis();
        System.out.println("工具类时间"+(l2-l1));
        System.out.println("普通时间"+(l1-l));
        System.out.println(dateTime);
        System.out.println("工具类时间"+createDate);
    }
}
