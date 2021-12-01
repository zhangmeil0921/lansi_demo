package demo.common.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.processing.OperationManager;
import com.qiniu.qvs.StreamManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

/**
 * @author Zml
 * @createDate 2021-09-17
 */
public class AVthumbForMpeg4 {
    //获取授权对象
    Auth auth = Auth.create("PPi8GMBRuyeVWvmH0zTsM7WDrBz97qWeo9puAHld","H5gQn4RPKS6c87uw6CIoVXsTm-NJF_Q2DGtg1Ef9");
    //执行操作的管理对象
    OperationManager operationMgr = new OperationManager(auth, new Configuration(Zone.zone0()));
    /**
     * Test Method
     * @param args
     */
    public static void main(String[] args) {
        new AVthumbForMpeg4().transcoding();
    }
    /**
     * 转码
     */
    void transcoding() {
        String bucket = "test1";          //存储空间名称
        String key = "mpeg_4_type.mp4";         //存储空间中视频的文件名称
        String newKey = "H264_type.mp4";        //转码后，另存的文件名称
        String pipeline = "admin_merge_radio";  //处理队列

        String saveAs = UrlSafeBase64.encodeToString(bucket + ":" + newKey);        //saveas接口 参数
        String fops = "avthumb/mp4/vcodec/libx264|saveas/" + saveAs;                //处理命令 avthumb 和 saveas 通过管道符 |  进行连接

        try {
            //执行转码和另存 操作
            String persistentId = operationMgr.pfop(bucket, key, fops, new StringMap().put("persistentPipeline", pipeline));
            System.out.println(persistentId);
        } catch (QiniuException e) {
            String errorCode = String.valueOf(e.response.statusCode);
            System.out.println(errorCode);
            e.printStackTrace();
        }
    }

    void uploadStream(){
        String accessKey = "PPi8GMBRuyeVWvmH0zTsM7WDrBz97qWeo9puAHld"; // 替换成自己 Qiniu 账号的 AccessKey.
        String secretKey = "H5gQn4RPKS6c87uw6CIoVXsTm-NJF_Q2DGtg1Ef9"; // 替换成自己 Qiniu 账号的 SecretKey.
        Auth auth = Auth.create(accessKey, secretKey);
        StreamManager streamManager = new StreamManager(auth);

//        streamManager.createStream("e","d");
    }
}
