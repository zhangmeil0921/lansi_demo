package demo.common.video;


import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.opencv.opencv_core.IplImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.opencv.global.opencv_core.cvReleaseImage;
import static org.bytedeco.opencv.global.opencv_imgproc.cvSmooth;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;

/**
 * @author Zml
 * @createDate 2021-09-22
 */
public class JavaCV {
    // the image's path;
    final static String imagePath = "/Users/lansi/picture/wei.jpg/";
    // the vedio's path and filename;
    final static String vedioPath = "/Users/lansi/Movies/Videos/Media.localized/";
    final static String vedioName = "1631873418781292.mp4";


    public static void main(String[] args) throws Exception {
        System.setProperty("org.bytedeco.javacpp.logger.debug","true");
//        smooth(imagePath);
        grabberFFmpegImage(vedioPath + vedioName, vedioPath
                , vedioName, 30);

    }

    // the method of compress image;
    public static void smooth(String fileName) {
        IplImage iplImage = cvLoadImage(fileName);
        if (iplImage != null) {
            cvSmooth(iplImage, iplImage);
            cvSaveImage(fileName, iplImage);
            cvReleaseImage(iplImage);
        }
    }

    // grab ffmpegImage from vedio;
    public static void grabberFFmpegImage(String filePath, String fileTargetPath
            , String fileTargetName, int grabSize) throws Exception{
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        ff.start();
        for (int i = 0; i < grabSize; i++){
            Frame frame = ff.grabImage();
            filePath = filePath.replace(".mp4","");
            fileTargetName = fileTargetName.replace(".mp4","");
            doExecuteFrame(frame, filePath, fileTargetName, i);
        }
        ff.stop();
    }

    // grab frame from vedio;
    public static void doExecuteFrame(Frame frame, String targetFilePath, String targetFileName, int index) {
        if ( frame == null || frame.image == null) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        String imageMat = "jpg";
        String fileName = targetFilePath + File.pathSeparator + targetFileName + "_" + index + "." + imageMat;
        BufferedImage bi = converter.getBufferedImage(frame);
        File output = new File(fileName);
        try{
            ImageIO.write(bi, imageMat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取指定视频的帧并保存为图片至指定目录
     *
     * @param videofile 源视频文件路径
     * @param framefile 截取帧的图片存放路径
     * @throws Exception▏
     */
    public static void fetchFrame(String videofile, String framefile)
            throws Exception {
        long start = System.currentTimeMillis();
        File targetFile = new File(framefile);
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        ff.start();
        int lenght = ff.getLengthInFrames();
        int i = 0;
        Frame f = null;
        while (i < lenght) {
            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabFrame();
            if ((i > 5) && (f.image != null)) {
                break;
            }
            i++;
        }
        IplImage img = cvLoadImage(videofile);
        int owidth = img.width();
        int oheight = img.height();
        // 对截取的帧进行等比例缩放
        int width = 800;
        int height = (int) (((double) width / owidth) * oheight);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
//                0, 0, null);
        ImageIO.write(bi, "jpg", targetFile);
        //ff.flush();
        ff.stop();
        //System.out.println(System.currentTimeMillis() - start);
    }
}
