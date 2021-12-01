package demo.common.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

/**
 * 操作七牛云
 * @author Zml
 * @createDate 2021-06-29
 */
@Service
public class OperateQiNiu {
    private static String accessKey = "QLI4RbpMdWV0wTgbdmU2FgJJYOr72oElhGOhPVnj";
    private static String secretKey = "qBbs0igIBN8IDwP4KsR2nGQv9AboxV1vu7sJil8v";
    private static String bucket = "store-demo";

    public static Auth getAuth(){
        return Auth.create(accessKey,secretKey);
    }

    /**
     * 修改文件类型
     * @param zone
     * @param key
     * @param auth
     * @param bucket
     * @param newMimeType
     */
    public static void editFileType(Zone zone, String key, Auth auth, String bucket, String newMimeType) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        // 修改文件类型
        try {
            bucketManager.changeMime(bucket, key, newMimeType);
        } catch (QiniuException ex) {
            System.out.println(ex.response.toString());
        }
    }

    /**
     * 移动文件
	 * @param zone
	 * @param auth
	 * @param fromBucket
	 * @param fromKey
	 * @param toBucket
	 * @param toKey
	 */
    public static void move(Zone zone,Auth auth,String fromBucket,String fromKey,String toBucket,String toKey) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.move(fromBucket, fromKey, toBucket, toKey);
        } catch (QiniuException ex) {
            // 如果遇到异常，说明移动失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     * 复制文件
     * @param zone
     * @param auth
     * @param fromBucket
     * @param fromKey
     * @param toBucket
     * @param toKey
     */
    public static void copy(Zone zone,Auth auth,String fromBucket,String fromKey,String toBucket,String toKey) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.copy(fromBucket, fromKey, toBucket, toKey);
        } catch (QiniuException ex) {
            // 如果遇到异常，说明复制失败
            System.err.println(ex.code());
        }
    }

    /**
     * 刪除文件
     * @param key
     * @param auth
     * @param bucket
     */
    public static void delete(Zone zone,String key,Auth auth,String bucket) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            // 如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }


    /**
     * 获取文件列表
     * @param zone
     * @param auth   授权凭证
     * @param bucket  存储空间名
     * @param prefix  文件名前缀
     * @param limit   每次迭代的长度限制，最大1000，推荐值 1000
     * @param delimiter  指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
     * @return
     */
    public static BucketManager.FileListIterator getFileList(Zone zone, Auth auth, String bucket, String prefix, int limit, String delimiter) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        // 列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit,
                delimiter);
        while (fileListIterator.hasNext()) {
            // 处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                System.out.println(item.key);
                System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);
            }
        }
        return fileListIterator;
    }

    /**
     * 设置文件生存时间
     * @param zone
     * @param key
     * @param auth
     * @param bucket
     */
    public static void setAliveTime(Zone zone,String key,Auth auth,String bucket,int days) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.deleteAfterDays(bucket, key, days);
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }

    /**
     * 抓取网络资源到空间
     * @param zone
     * @param auth
     * @param bucket
     * @param key
     * @param remoteSrcUrl
     * @return
     */
    public static FetchRet fetchToSpace(Zone zone, Auth auth, String bucket, String key, String remoteSrcUrl) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        //String remoteSrcUrl = "http://devtools.qiniu.com/qiniu.png";
        BucketManager bucketManager = new BucketManager(auth, cfg);
        // 抓取网络资源到空间
        try {
            FetchRet fetchRet = bucketManager.fetch(remoteSrcUrl, bucket, key);
            System.out.println(fetchRet.hash);
            System.out.println(fetchRet.key);
            System.out.println(fetchRet.mimeType);
            System.out.println(fetchRet.fsize);
            return fetchRet;
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
        return null;
    }

}
