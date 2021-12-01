package demo.common.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * 获取七牛上的内容
 * @author Zml
 * @createDate 2021-06-29
 */
@Service
public class FetchQiNiu {

    private static String accessKey = "QLI4RbpMdWV0wTgbdmU2FgJJYOr72oElhGOhPVnj";
    private static String secretKey = "qBbs0igIBN8IDwP4KsR2nGQv9AboxV1vu7sJil8v";
    private static String bucket = "store-demo";

    public static Auth getAuth(){
        return Auth.create(accessKey,secretKey);
    }

    /**
     * 公有空间返回文件URL
     * @param fileName
     * @param domainOfBucket
     * @return
     */
    public static String publicFile(String fileName,String domainOfBucket) {
        String encodedFileName=null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        System.out.println(finalUrl);
        return finalUrl;
    }

    /**
     * 私有空间返回文件URL
     * @param auth
     * @param fileName
     * @param domainOfBucket
     * @param expireInSeconds
     * @return
     */
    public static String privateFile(Auth auth,String fileName,String domainOfBucket,long expireInSeconds) {
        String encodedFileName=null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        System.out.println(finalUrl);
        return finalUrl;
    }

    /**
     * 获取文件信息
     * @param zone
     * @param key
     * @param auth
     * @param bucket
     * @return
     */
    public static FileInfo getFileInfo(Zone zone, String key, Auth auth, String bucket) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, key);
            System.out.println(fileInfo.hash);
            System.out.println(fileInfo.fsize);
            System.out.println(fileInfo.mimeType);
            System.out.println(fileInfo.putTime);
            return fileInfo;
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
        return null;
    }

    /**
     * 生成上传凭证
     */
    public static String getUploadCredential() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
        return upToken;
    }

    /**
     * 获取客户端覆盖上传凭证
     *
     * @param fileKey 被覆盖的文件名
     */
    public static String getOverloadCredential(String fileKey) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, fileKey);
        System.out.println(upToken);
        return upToken;
    }


    /**
     * 获取客户端上传凭证(自定义返回值)
     * @param returnBody 自定义返回值
     * @param expireSeconds 有效期(秒)
     * @return
     */
    public static String getUploadCredential(String returnBody,long expireSeconds) {
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody",returnBody);
//		putPolicy.put("returnBody",
//				"{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        System.out.println(upToken);
        return upToken;
    }

    /**
     *  获取客户端上传凭证(自定义回调application/json格式)
     *
     * @param callbackBody 自定义回调
     *            application/json格式
     * @param callbackUrl 回调地址
     * @param expireSeconds 有效期
     */
    public static void getUploadCredential(String callbackBody, String callbackUrl,long expireSeconds) {
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
//		putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
//		putPolicy.put("callbackBody",
//				"{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        putPolicy.put("callbackUrl", callbackUrl);
        putPolicy.put("callbackBody",callbackBody);
        // putPolicy.put("callbackBody",
        // "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize),\"user\":\"$(x:user)\",\"age\",$(x:age)}");
        putPolicy.put("callbackBodyType", "application/json");
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        // String upToken = auth.uploadToken(bucket, key, expireSeconds, putPolicy,
        // false);//false:允许添加额外参数
        System.out.println(upToken);
    }

    /**
     * 获取客户端上传凭证(自定义回调application/x-www-form-urlencoded格式)
     *
     * @param callbackBody 自定义回调
     *            application/x-www-form-urlencoded格式
     * @param callbackUrl 回调地址
     * @param expireSeconds 有效期
     */
    public static void getUploadCredential2(String callbackBody, String callbackUrl,long expireSeconds) {
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
//		putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
//		putPolicy.put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)");
        putPolicy.put("callbackUrl", callbackUrl);
        putPolicy.put("callbackBody", callbackBody);
//		long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        System.out.println(upToken);
    }

}
