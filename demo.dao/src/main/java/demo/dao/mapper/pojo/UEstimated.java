package demo.dao.mapper.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* Created by Mybatis Generator on 2022/07/20
*/
@TableName(value = "lansi_fdh.u_estimated")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UEstimated implements Serializable {

    @TableId( value = "u_estimated_id")
    private Integer uEstimatedId;

    @TableField(value  = "housingname")
    private String housingname;

    @TableField(value  = "area")
    private String area;

    @TableField(value  = "plate")
    private String plate;

    @TableField(value  = "reportno")
    private String reportno;

    @TableField(value  = "houseaddress")
    private String houseaddress;

    @TableField(value  = "allfloor")
    private Integer allfloor;

    @TableField(value  = "flevel")
    private Integer flevel;

    @TableField(value  = "acreage")
    private BigDecimal acreage;

    @TableField(value  = "estimateddate")
    private Date estimateddate;

    @TableField(value  = "estimatedprice")
    private Integer estimatedprice;

    @TableField(value  = "estimatedusd")
    private BigDecimal estimatedusd;

    @TableField(value  = "account_id")
    private Integer accountId;

    /**
     * 朝向
     */
    @TableField(value = "shore")
    private String shore;

    /**
     * 装修情况
     */
    @TableField(value = "decoration")
    private String decoration;

    /**
     * 室
     */
    @TableField(value  = "bedroom")
    private Integer bedroom;

    /**
     * 厅
     */
    @TableField(value  = "livingroom")
    private Integer livingroom;

    /**
     * 卫
     */
    @TableField(value  = "toilet")
    private Integer toilet;

    /**
     * 电梯数量
     */
    @TableField(value  = "elevator")
    private Integer elevator;

    /**
     * 户数
     */
    @TableField(value = "house_count")
    private Integer houseCount;

    /**
     * 0:未删除
1:已删除
     */
    @TableField(value  = "is_delete")
    private Integer isDelete;
}
