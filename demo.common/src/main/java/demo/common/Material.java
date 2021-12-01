package demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zml
 * @createDate 2021-07-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material implements Serializable {

 private String title;//图文消息的标题

    private String thumb_media_id;//图文消息的封面图片素材id（必须是永久mediaID）

    private String author;//作者

    private String digest;//图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空

    private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS

    private String url;//图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL

    private int show_cover_pic;//是否显示封面，0为false，即不显示，1为true，即显示
}
