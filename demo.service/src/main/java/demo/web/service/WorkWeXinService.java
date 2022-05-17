package demo.web.service;

import response.AccessTokenResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 1:56 下午
 */
public interface WorkWeXinService {

    /**
     * 企业微信授权登录
     *
     * @param corpid     第三方用户唯一凭证
     * @param corpsecret    第三方用户唯一凭证密钥 AppSecret
     * @return
     */
    @GET("cgi-bin/gettoken")
    Call<AccessTokenResult> getAccessToken(@Query("corpid") String corpid, @Query("corpsecret") String corpsecret);


}
