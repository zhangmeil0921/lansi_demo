package demo.common.video;

import lombok.Data;

/**
 * @author Zml
 * @createDate 2021-09-17
 */
@Data
public class BStreamer {

    private int width = 640;
    private int height = 480;
    private String url;

    public BStreamer(String url) {
        this.url = url;
    }

    public BStreamer(String url, int w, int h) {
        this.url = url;
        if (w > 0 && h > 0) {
            this.width = w;
            this.height = h;
        }
    }
}
