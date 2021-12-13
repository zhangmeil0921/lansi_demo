package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 全局错误返回码
 * @author Sean
 * @createDate 2021-09-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody implements Serializable {
    /**
     * 返回码
     */
    private Integer errcode=0;

    /**
     * 错误消息
     */
    private String errmsg="";

    public Boolean isSuccess() {
        return errcode.equals(0) && ("OK".equals(errmsg.toUpperCase()) || StringUtils.isEmpty(errmsg));
    }
}
