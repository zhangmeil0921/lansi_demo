package demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zml
 * @createDate 2021-07-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;
}
