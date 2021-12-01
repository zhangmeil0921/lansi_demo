package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SRoleFunctionOperateDO {
    /**
     * 菜单编码
     */
    private String operateCode;
    /**
     * 菜单名称
     */
    private String operateName;
    /**
     * 是否显示
     */
    private Boolean isShow;

}
