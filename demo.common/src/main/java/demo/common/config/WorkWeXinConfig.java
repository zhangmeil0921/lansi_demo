package demo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 企业微信相关的配置
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 1:31 下午
 */
@Data
@ConfigurationProperties(prefix = "spring.work.wexin")
public class WorkWeXinConfig implements Serializable {

    /**
     * 微信公众平台账户AppId
     */
    private String corpId;

    /**
     * 微信公众平台账户AppSecret
     */
    private String contactsCorpSecret;

}
