package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zml
 * @createDate 2021-07-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialNewsItem {
    private String mediaId;
    private String title;
    private String thumbMediaId;
    private Boolean isShowCoverPicture;
    private String author;
    private String digest;
    private String content;
    private String url;
    private String contentSourceUrl;
    private Boolean isNeedOpenComment;
    private Boolean isOnlyFansCanComment;
    private Long createTime;
    private Long updateTime;
    private String operateUser;
}
