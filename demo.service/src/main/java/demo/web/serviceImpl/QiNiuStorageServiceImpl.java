package demo.web.serviceImpl;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import demo.web.service.filter.QiniuStorageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Zml
 * @createDate 2021-07-23
 */
@Service
@RequiredArgsConstructor
public class QiNiuStorageServiceImpl implements QiniuStorageService {

    private static String accessKey = "QLI4RbpMdWV0wTgbdmU2FgJJYOr72oElhGOhPVnj";
    private static String secretKey = "qBbs0igIBN8IDwP4KsR2nGQv9AboxV1vu7sJil8v";
    private static String bucket = "store-demo";
    //上传文件的路径
    private static String FilePath = "/.../...";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Auth getAuth(){
        return Auth.create(accessKey,secretKey);
    }

    //设置转码操作参数
    String fops = "avthumb/mp4/s/640x360/vb/1.25m";
    //设置转码的队列
    String pipeline = "yourpipelinename";

    //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
    String urlbase64 = UrlSafeBase64.encodeToString("目标Bucket_Name:自定义文件key");
    String pfops = fops +"|saveas/"+ urlbase64;

    //密钥配置
    Auth auth = getAuth();
    // 构造一个带指定Zone对象的配置类
    Configuration cfg = new Configuration(Zone.zone0());
    // ...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

    //上传策略中设置persistentOps字段和persistentPipeline字段
    public String getUpToken(){
        return auth.uploadToken(bucket,null,3600,new StringMap()
                .putNotEmpty("persistentOps", pfops)
                .putNotEmpty("persistentPipeline", pipeline), true);
    }

    public void upload() throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, null, getUpToken());
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


    @Override
    public String uploadVideo(MultipartFile files) {
        String key ="test1";
        CommonsMultipartFile picture = (CommonsMultipartFile) files;
        DiskFileItem diskFileItem = (DiskFileItem) picture.getFileItem();
        File file = diskFileItem.getStoreLocation();
        try {
            String fileName = picture.getOriginalFilename();
            //设置转码操作参数
            String fops = "avthumb/mp4/vcodec/libx264";
            //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
            String urlbase64 = "";
            if(fileName.endsWith(".mp4")){
                 urlbase64 = UrlSafeBase64.encodeToString(bucket  + ":" + fileName);
            }else{
                urlbase64 = UrlSafeBase64.encodeToString(bucket  + ":" + fileName + ".mp4");
            }

            String pfops = fops + "|saveas/" + urlbase64;
            String upToken = auth.uploadToken(bucket , null, 3600, new StringMap().putNotEmpty("persistentOps", pfops));
            key = key + ".mp4";
            //调用put方法上传
            Response response = uploadManager.put(file, key, upToken);
            //打印返回的信息
            System.out.println("-----success---" + response.bodyString());
            DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);
            return ret.key;
        } catch (QiniuException e) {
            logger.error("upload file to qiniu cloud storage failed", e);
        }

        return null;
    }
}
