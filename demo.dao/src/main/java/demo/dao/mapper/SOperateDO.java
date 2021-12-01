package demo.dao.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SOperateDO {
    /**
     * 按钮ID
     */
    private String operateId;
    /**
     * 实际按钮Id
     */
    private String mainOperateId;
    /**
     * 按钮编码
     */
    private String operateCode;
    /**
     * 按钮名称
     */
    private String operateName;

}
