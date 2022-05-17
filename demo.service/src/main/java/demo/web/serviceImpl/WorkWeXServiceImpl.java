package demo.web.serviceImpl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.micro.service.driven.wechat.mp.entity.request.MessageDecryptReq;
import demo.common.RetrofitBuilder;
import demo.web.service.WorkWeXService;
import demo.web.service.WorkWeXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import request.WorkWexinEntranceReq;
import response.AccessTokenResult;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/15 6:01 下午
 */
@Service
public class WorkWeXServiceImpl implements WorkWeXService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WorkWeXinService workWeXinService = RetrofitBuilder.create().create(WorkWeXinService.class);

    @Value("${spring.work.wexin.corpid}")
    private String corpid;

    @Value("${spring.work.wexin.contacts.corpsecret}")
    private String corpsecret;

    @Override
    public void entrance(WorkWexinEntranceReq request) {
        String result;
        // 验证服务器请求
        if (HttpMethod.GET.name().equals(request.getRequest().getMethod().toUpperCase())) {
            logger.info("微信验证服务器请求 参数>>> {}", request);
            result = request.getEchostr();
        }
        // 其他请求
        else {
            try {
                String inputStreamString = IoUtil.read(request.getRequest().getInputStream()).toString("UTF-8");
                request.setInputStream(inputStreamString);
                logger.info("接收微信推送消息 签名:{} 随机字符串:{} 数据:{}", request.getMsg_signature(), request.getNonce(), inputStreamString);
                result = receiveHandler(request);
            } catch (Exception e) {
                logger.error("处理微信推送消息错误!", e);
                result = "success";
            }
        }
        if (StrUtil.isNotEmpty(result)) {
            request.getResponse();
        }
    }

    private String receiveHandler(WorkWexinEntranceReq request) {
        // 解密
        try {
            MessageDecryptReq messageDecryptReq = new MessageDecryptReq();
            messageDecryptReq.setMessage(request.getInputStream());
            messageDecryptReq.setMsgSignature(request.getMsg_signature());
            messageDecryptReq.setNonce(request.getNonce());
            messageDecryptReq.setTimestamp(request.getTimestamp());
//            String decryptMessage = workWeXinMpTemplate.decryptMessage(messageDecryptReq);
//            return weChatMpTemplate.executeMessage(decryptMessage, new MessageCallBack() {
//                @Override
//                public String massMessage(MassMessageResult result) {
//                    int results  = 0;
//                    MassMessageResult.ArticleUrlResult articleUrlResult = result.getArticleUrlResult();
//                    if (ObjectUtil.isNull(articleUrlResult)) {
//                        return "";
//                    }
//                    List<MaterialSourceDo> materialSourceDos = new ArrayList<>();
//                    List<MassMessageResult.ArticleUrlResultItem> resultItems = articleUrlResult.getResultItems();
//                    for (int i = 0; i < resultItems.size(); i++) {
//                        MaterialSourceDo source = getMaterialSourceTitle(resultItems.get(i).getArticleUrl());
//                        Integer hasExists = materialSourceMapper.getHasExistsMaterialSourceTitle(source.getTitle());
//                        if (hasExists >0){
//                            //数据进行修改
//                            Example example = new Example(MaterialSourceDo.class);
//                            example.createCriteria().andEqualTo("title", source.getTitle());
//                            materialSourceMapper.updateByExample(MaterialSourceDo.builder().createTime(StrUtil.isEmpty(Convert.toStr(source.getCreateTime())) ? LocalDateTime.now() : Convert.toLocalDateTime(source.getCreateTime())).build(),example);
//                        }else{
//                            //数据进行新增
//                            materialSourceDos.add(MaterialSourceDo.builder().url(resultItems.get(i).getArticleUrl())
//                                    .materialVirtualId(serialNumberService.generateSerialNumber(SerialNumberTypeEnum.GZH_SERIAL_NUMBER))
//                                    .author(source.getAuthor()).createTime(source.getCreateTime()).title(source.getTitle())
//                                    .isDelete(0).isOnline(0).views(0).operateUser(AccountThreadLocal.get() == null ? "自动" : AccountThreadLocal.get().getNickName()).build());
//                        }
//                        results += materialSourceMapper.insertList(materialSourceDos);
//                    }
//                    if (results == resultItems.size()){
//                        return "success";
//                    }
                    return  "fail";
//                }
//            });
        } catch (Exception e) {
            logger.error("同步文章到MySQL时失败!", e);
            return "fail";
        }
    }

    /**
     * 获取企业微信对象token
     * @return
     */
    public AccessTokenResult getToken(){
        Call<AccessTokenResult> token = workWeXinService.getAccessToken(corpid, corpsecret);
        Response<AccessTokenResult> response = null;
        try {
            response = token.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccessTokenResult result = response.body();
        return result;
    }
}
