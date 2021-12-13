package demo.web.service;

/**
 * 企业微信平台
 * @author ZML
 * @version 1.0
 * @date 2021/12/3 9:40 上午
 */
public interface MpOptions {

    /**
     * 获取企业微信的全局唯一接口调用凭据
     * 参考文档地址:https://work.weixin.qq.com/api/doc/90000/90135/91039
     *
     * @return
     */
    String getAccessToken();
}
