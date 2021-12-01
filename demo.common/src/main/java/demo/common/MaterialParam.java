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
public class MaterialParam implements Serializable {

    private String type;//素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）

    private int offset;//从全部素材的该偏移位置开始返回，0表示从第一个素材 返回

    private int count;//返回素材的数量，取值在1到20之间
}
