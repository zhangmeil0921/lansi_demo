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
 * 商品表
 * @author Zml
 * @createDate 2021-07-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product")
public class ProductDo implements Serializable {

    /**
     * 商品ID
     */
    @TableId(value = "product_id",type = IdType.AUTO)
    private Integer productId;
    /**
     * 商品名称
     */
    @TableField(value = "product_name")
    private String productName;
}
