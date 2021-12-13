package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 企业微信token返回对象
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 2:12 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccessTokenResult extends ResponseBody implements Serializable {

    /**
     * 凭证有效时间,单位:秒
     */
    private Integer expires_in;

    /**
     * 获取到的凭证
     */
    private String access_token;
}
