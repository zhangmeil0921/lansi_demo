package demo.common.video;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Zml
 * @createDate 2021-09-17
 */
public class FFmpegPusher extends BStreamer{

    public FFmpegPusher(String url) {
        super(url);
    }

    public FFmpegPusher(String url, int w, int h) {
        super(url, w, h);
    }
    private Process process;

    public void push(){
        String basePath="/Users/lansi/Movies/Videos/Media.localized/";
        String videoPath=String.format("%s1631873418781292.mp4",basePath);
        String ffmpegPath=String.format("%sffmpeg",basePath);
        try {
            // 视频切换时，先销毁进程，全局变量Process process，方便进程销毁重启，即切换推流视频
            if(process != null){
                process.destroy();
                System.out.println(">>>>>>>>>>推流视频切换<<<<<<<<<<");
            }
            // ffmpeg开头，-re代表按照帧率发送，在推流时必须有
            // 指定要推送的视频
            // 指定推送服务器，-f：指定格式
            String command =String.format("%s -re -i %s -f rtsp %s",
                    ffmpegPath,videoPath,getUrl());
            System.out.println("ffmpeg推流命令：" + command);
            // 运行cmd命令，获取其进程
            Runtime.getRuntime().exec(String.format("sudo chmod -R 777 %s",ffmpegPath));
            process = Runtime.getRuntime().exec(command);
            // 输出ffmpeg推流日志
            BufferedReader br= new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("视频推流信息[" + line + "]");
            }
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
