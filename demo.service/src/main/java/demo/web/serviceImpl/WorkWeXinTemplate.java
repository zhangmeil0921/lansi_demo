package demo.web.serviceImpl;

import com.micro.service.driven.wechat.mp.exception.WeChatMpDriveException;
import demo.common.RetrofitBuilder;
import demo.web.service.MpOptions;
import demo.web.service.WorkWeXinService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import response.AccessTokenResult;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 3:11 下午
 */
public class WorkWeXinTemplate implements MpOptions {

    private final WorkWeXinService workWeXinService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("spring.work.wexin.corpid")
    private String  corpid;

    @Value("spring.work.wexin.contacts.corpsecret")
    private String corpsecret;

    public WorkWeXinTemplate(WorkWeXinService workWeXinService) {
        this.workWeXinService = RetrofitBuilder.create().create(WorkWeXinService.class);
    }

    @Override
    public String getAccessToken() {
        Call<AccessTokenResult> call = this.workWeXinService.getAccessToken(corpid, corpsecret);
        Response<AccessTokenResult> response = null;
        try {
            response = call.execute();
            AccessTokenResult tokenResult = response.body();
            if (ObjectUtils.isNotEmpty(tokenResult)) {
                if (tokenResult.isSuccess()) {
                    String accessToken = tokenResult.getAccess_token();
                    return accessToken;
                } else {
                    throw new WeChatMpDriveException(new RuntimeException(tokenResult.getErrmsg()));
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
