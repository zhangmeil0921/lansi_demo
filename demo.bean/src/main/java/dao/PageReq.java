package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zml
 * @createDate 2021-07-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReq implements Serializable {

    /**
     * 页码
     */
    private Integer offset;

    /**
     * 每页显示条数
     */
    private Integer limit;
}
