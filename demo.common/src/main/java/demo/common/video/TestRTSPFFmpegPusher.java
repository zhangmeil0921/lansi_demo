package demo.common.video;

/**
 * @author Zml
 * @createDate 2021-09-17
 */
public class TestRTSPFFmpegPusher {

    public static void main(String[] args) throws Exception {
        String url="rtsp://192.168.56.1/test";
        FFmpegPusher pusher=new FFmpegPusher(url);
        pusher.push();
    }
}
