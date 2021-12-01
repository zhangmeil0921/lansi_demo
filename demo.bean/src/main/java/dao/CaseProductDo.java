package dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 案例与商品中间表
 * @author Zml
 * @createDate 2021-07-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "case_product")
public class CaseProductDo implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /**
     * 案例ID
     */
    @TableField(value = "case_id")
    private Integer caseId;

    /**
     * 商品ID
     */
    @TableField(value = "product_id")
    private Integer productId;
}
