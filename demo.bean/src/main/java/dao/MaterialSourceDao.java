package dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 微信素材对象
 * @author Zml
 * @createDate 2021-07-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("material_source")
public class MaterialSourceDao implements Serializable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private  Integer id;
    /**
     *  账号类型1：楼市问号 2：房导航 3：一房一万 4:乐享屋
     */
    @TableField(value = "account_type")
    private  Integer accountType;
    /**
     *  文件id
     */
    @TableField(value = "media_id")
    private  String mediaId;
    /**
     * 文件类型：图片（image）、视频（video）、语音 （voice）、图文（news）
     */
    @TableField(value = "type")
    private  String type;
    /**
     * 文件名称
     */
    @TableField(value = "name")
    private  String name;
    /**
     * 图文消息的标题
     */
    @TableField(value = "title")
    private  String title;
    /**
     * 图文消息的封面图片素材id（必须是永久mediaID）
     */
    @TableField(value = "thumb_media_id")
    private  String thumbMediaId;
    /**
     * 是否显示封面，0为false，即不显示，1为true，即显示
     */
    @TableField(value = "show_cover_pic")
    private  Integer showCoverPic;
    /**
     * 作者
     */
    @TableField(value = "author")
    private  String author;
    /**
     * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
     */
    @TableField(value = "digest")
    private  String digest;
    /**
     *  图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
     */
    @TableField(value = "content")
    private  String content;
    /**
     * 封面图url
     */
    @TableField(value = "thumb_url")
    private  String thumbUrl;
    /**
     * 图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
     */
    @TableField(value = "url")
    private  String url;
    /**
     * 图文消息的原文地址，即点击“阅读原文”后的URL
     */
    @TableField(value = "content_source_url")
    private  String contentSourceUrl;
    /**
     * 这篇图文消息素材的最后更新时间
     */
    @TableField(value = "update_time")
    private  LocalDateTime updateTime;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private  LocalDateTime createTime;
    /**
     * 文章类型
     */
    @TableField(value = "article_type")
    private  String articleType;
    /**
     * 楼盘id
     */
    @TableField(value = "newdiskid")
    private  Integer newDiskId;
    /**
     * 是否轮播（1轮播 0不轮播）
     */
    @TableField(value = "is_carousel")
    private  Integer isCarousel;
    /**
     * 是否上线（1上线 0不上线）
     */
    @TableField(value = "is_online")
    private  Integer isOnline;
    /**
     * 文章分享图
     */
    @TableField(value = "share_img")
    private  String shareImg;
    /**
     * 文章真实访问地址
     */
    @TableField(value = "real_url")
    private  String realUrl;

}
