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
 * 设计师案例
 * @author Zml
 * @createDate 2021-07-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "designer_case")
public class DesignerCaseDo implements Serializable {
    /**
     * 案例ID
     */
    @TableId(value = "case_id",type = IdType.AUTO)
    private Integer caseId;
    /**
     * 设计师ID
     */
    @TableField(value = "designer_id")
    private Integer designerId;
    /**
     * 案例所有权案例ID
     */
    @TableField(value = "owner_designer_id")
    private Integer  ownerDesignerId;
    /**
     * 楼盘ID
     */
    @TableField(value = "newdisk_id")
    private Integer  newdiskId;
    /**
     * 楼盘名称
     */
    @TableField(value = "newdisk_name")
    private Integer newdiskName;
    /**
     * 标题
     */
    @TableField(value = "title")
    private Integer title;
    /**
     * 封面图
     */
    @TableField(value = "cover_picture")
    private Integer  coverPicture;
    /**
     * 封面图宽
     */
    @TableField(value = "cover_picture_width ")
    private Integer coverPictureWidth;
    /**
     * 封面图高
     */
    @TableField(value = "cover_picture_height")
    private Integer  coverPictureHeight;
    /**
     * 卧室图片
     */
    @TableField(value = "bedroom_picture")
    private Integer bedroomPicture;
    /**
     * 客厅图片
     */
    @TableField(value = "livingroom_picture")
    private Integer livingroomPicture;
    /**
     * 餐厅图片
     */
    @TableField(value = "diningroom_picture")
    private Integer diningroomPicture;
    /**
     * 价格
     */
    @TableField(value = "case_price")
    private Integer casePrice;
    /**
     * 面积
     */
    @TableField(value = "acreage")
    private Integer acreage;
    /**
     * 室
     */
    @TableField(value = "bedroom")
    private Integer bedroom;
    /**
     * 厅
     */
    @TableField(value = "livingroom")
    private Integer livingroom;
    /**
     * 厨
     */
    @TableField(value = "kitchen")
    private Integer kitchen;
    /**
     * 卫
     */
    @TableField(value = "owner_designer_id")
    private Integer toilet;
    /**
     * 点赞数
     */
    @TableField(value = "like_count")
    private Integer likeCount;
    /**
     * 关注收藏数
     */
    @TableField(value = "follow_count")
    private Integer followCount;
    /**
     * 浏览量
     */
    @TableField(value = "views_count")
    private Integer viewsCount;
    /**
     * 案例风格 一个案例只有一个风格
     */
    @TableField(value = "style")
    private Integer style;
    /**
     * 业主诉求
     */
    @TableField(value = "owner_demand")
    private Integer ownerDemand;
    /**
     * 设计思路
     */
    @TableField(value = "design_ideas")
    private Integer designIdeas;
    /**
     * 是否上线
     */
    @TableField(value = "is_online")
    private Integer isOnline;
    /**
     * 是否选中
     */
    @TableField(value = "is_selected")
    private Integer isSelected;
    /**
     * 是否包含VR案例链接
     */
    @TableField(value = "is_vr")
    private Integer isVr;
    /**
     * 案例类型
     */
    @TableField(value = "case_type")
    private Integer caseType;
    /**
     * 酷家乐VR案例场景值
     */
    @TableField(value = "vr_scene_id")
    private Integer vrSceneId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Integer createTime;
    /**
     * 版权文案
     */
    @TableField(value = "copyright")
    private Integer copyright;
    /**
     * 操作人
     */
    @TableField(value = "operate_user")
    private Integer operateUser;
    /**
     * 操作时间
     */
    @TableField(value = "operate_time")
    private Integer operateTime;


}
