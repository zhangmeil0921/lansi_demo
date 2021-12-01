package demo.common.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import java.io.IOException;

/**
 * @author Zml
 * @createDate 2021-09-17
 */
public class UploadDemo {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "PPi8GMBRuyeVWvmH0zTsM7WDrBz97qWeo9puAHld";
    String SECRET_KEY = "H5gQn4RPKS6c87uw6CIoVXsTm-NJF_Q2DGtg1Ef9";
    //要上传的空间
    String bucketname = "test_stream";
    //上传到七牛后保存的文件名
    String key = "longs.mp4";
    //上传文件的路径
    String FilePath = "/Users/lansi/Movies/Videos/Media.localized/1631873418781292.mp4";

    //设置转码操作参数
    String fops = "avthumb/mp4/s/640x360/vb/1.25m";
    //设置转码的队列
    String pipeline = "";

    //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
    String urlbase64 = UrlSafeBase64.encodeToString("firstStart.mp4");
    String pfops = fops +"|saveas/"+ urlbase64;

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager(new Configuration());

    //上传策略中设置persistentOps字段和persistentPipeline字段
    public String getUpToken(){
        return auth.uploadToken(bucketname,"longs.mp4",3600,new StringMap()
                .putNotEmpty("persistentOps", pfops)
                .putNotEmpty("persistentPipeline", pipeline), true);
    }
    public void upload() throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, "longs.mp4", getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }


    public static void main(String args[]) throws IOException{
        new UploadDemo().upload();
    }
}
