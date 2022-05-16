package request;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/15 5:56 下午
 */
@Data
public class WorkWexinEntranceReq {
    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 随机数
     */
    private String nonce;

    /**
     * 消息签名
     */
    private String msg_signature;

    /**
     * 请求签名
     */
    private String signature;

    /**
     * 随机字符串
     */
    private String echostr;

    /**
     * 响应对象
     */
    private HttpServletResponse response;

    /**
     * 请求对象
     */
    private HttpServletRequest request;

    /**
     * 请求流字符串
     */
    private String inputStream;
}
