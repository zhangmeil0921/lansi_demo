package demo.common.work.wexin;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.tencent.wework.Finance;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 企业微信拉取聊天记录
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 2:31 下午
 */
@Service
public class FinanceDemo {

//    @Value("spring.work.wexin.corpid")
    private static String corpid = "wwd08coe7d775abaaa";

//    @Value("spring.work.wexin.contacts.corpsecret")
    private static String secret = "zJ6k0naVVQ--gt9PUSSEvs03zW_nlDVmjAkPOTAfrew";

    private static String priKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
                    "MIIEowIBAAKCAQEAjzJfnYgy8gI/UdR9OMzMh2/Svtz6ynPJdgqWX0qCm7361g04\n" +
                    "Sz/g+aJGtqu8jRJNg3rxQhezoy5mc+//QDyL6sM0auASS+eWx2igxVahLhJEWef3\n" +
                    "2woMrb+OHs6nJf2jBcfA494KvgkxSYXU84vl8UCHwUI839SLBSLWofb8ccFEmy9W\n" +
                    "VSWY7EIUVV8pO8sLZ9uVWK1IrHHSrcGkx4SF913RDIxCTExj7bLqxNRklzWZgi3m\n" +
                    "bYoIsTG6dmsGkmxagGPEtuVOI2UjpjQw1WyrN+6o+2+qpOIuoHKF5vGrPbQBL1jU\n" +
                    "nshGs3MnwTWdxVOe+bw8a+VAqxpD5DlfdKamgQIDAQABAoIBAGNRbe3mPGeMVXyd\n" +
                    "I6kUqrs5PPNyc2OdwVpk53z6QfJhZyu1iZjvmkuqWN9z59f0nNyXlePgapDAqwC4\n" +
                    "sdJM7EKM17tU5HvPCc4O7ItSlYJN2yh8cnVy1+5ekOUfMeFwtPRaYpfpNowt9ghn\n" +
                    "kZbGLlsRBddt6KjaUv3h9vnpQ5hlhU53slh+Zsdrsselpy0sTCF0ulVQgioZ6lzg\n" +
                    "/Y61xCGxRWqq71UT/7EOZwIoD3NmLRZLE6vxm8uBrFvZZ5/jw9Z//8S+vgtRUgGj\n" +
                    "/5v55ftTG+EOsaz2Zvt+jghkaEAchcyy5LyUv09Ir9eOYfjNAgeCk9dQv6E+2BiR\n" +
                    "SvcUJEECgYEA5CvcoSpV66qC1T99/JqmqyTjORZ4ZKBJY9vos4uxNYfCbbhNJLDY\n" +
                    "QAbCC234nBuKQ+3cVKkWtjBwfNp90mEmIoN6sYv+SdtVe2ofhJEqLMTiGyZPi2Iv\n" +
                    "+vvRpFmiAUfWXqFzBy+3HSoTkRqbCytUIT7NXZZ3YwKbbIhyzABTzSkCgYEAoKlf\n" +
                    "Gsn4rQEfmitAGJpuiSahih6KV3K+S9olnPbwd04YOw4u5UmouhyC7N0z2K8AO6zo\n" +
                    "IkzCQtWQS1B1uaPE/stG7I/iy0CI6q46nSly5pajBBGQc+Y5ixRf23hEjGgca556\n" +
                    "1pDyMs0Nb0J2AGDcr8olrbT1KwBvg8oWHOlA4ZkCgYAmC+pONXD+SwBl7qBjbqY8\n" +
                    "A3qgGk8Y+GFEdXbn+XMjKfARu5mhdJuakYXpwfyiizUS/qaut0NCPfGD4Cr62Zgy\n" +
                    "SRo8YMuWJSyr15ZJ1KrjrDDHtiutYkH959+dOBT7ga8NOH6lxB8Ujd+VYopX4nG0\n" +
                    "2XQFFwHxUI36GwaJXcSbgQKBgEe4VERZNTHF9p2UASD6j62aGTLXP1qaVmj2ESRo\n" +
                    "+B/KNPbn9fdVUoUChU/Hz4VDWg9JuLbXHUFIpQl5+ZPNj/tOM3MXKF8jh/t7m57d\n" +
                    "CfX1+P+v95RFihqUFdabcb5cG5PPQ3bVbclP0FeCi7rPgrTWwMsypN91alKivAxb\n" +
                    "9CLBAoGBALfb5SEupOoyIUetiWhOc1vlmP+71rJEXUEYwm0CMmfKZRW+I1A8qTE+\n" +
                    "DVT6iUMVt6jj8L/YiMzjUh8y8HLcp4mlldzdfyjssZg6v2hABJcnID6o3DcTXA8k\n" +
                    "8uMmaZZ0qY4oUzwgZa7bf/C//3mKlRUMoBYOT/LAksZlySIBXoST\n" +
                    "-----END RSA PRIVATE KEY-----\n";

    public void demo() {
        long sdk = Finance.NewSdk();
        // 初始化
        Finance.Init(sdk, corpid, secret);
        long ret = 0;
        // 从指定的seq开始拉取消息，注意的是返回的消息从seq+1开始返回，seq为之前接口返回的最大seq值。首次使用请使用seq:0（这个值需要记录下来，以便下一次的拉去）
        int seq = 0;
        int limit = 60;
        long slice = Finance.NewSlice();
        ret = Finance.GetChatData(sdk, seq, limit, null, null, 3, slice);
        if (ret != 0) {
            System.out.println("getchatdata ret " + ret);
            return;
        }
        String getchatdata = Finance.GetContentFromSlice(slice);
        System.out.println(seq + "，拉取的聊天记录密文结果：" + getchatdata);
        JSONObject jo = new JSONObject(getchatdata);
        JSONArray chatdata = jo.getJSONArray("chatdata");
        System.out.println("消息数：" + chatdata.size());
        for (int i = 0; i < chatdata.size(); i++) {
            JSONObject data = new JSONObject(chatdata.get(i).toString());
            String encryptRandomKey = data.getStr("encrypt_random_key");
            String encryptChatMsg = data.getStr("encrypt_chat_msg");
            long msg = Finance.NewSlice();
            try {
                // 聊天记录密文解密
                String message = RSAEncrypt.decryptRSA(encryptRandomKey, priKey);
                ret = Finance.DecryptData(sdk, message, encryptChatMsg, msg);
                if (ret != 0) {
                    System.out.println("getchatdata ret " + ret);
                    return;
                }
                String plaintext = Finance.GetContentFromSlice(msg);
                System.out.println("decrypt ret:" + ret + " msg:" + plaintext);
                Finance.FreeSlice(msg);

                JSONObject plaintextJson = new JSONObject(plaintext);
                // 拉去媒体文件解密
                String msgtype = plaintextJson.getStr("msgtype");
                if ("mixed".equals(msgtype)) {
                    // 混合消息
                    JSONArray array = new JSONArray();
                    JSONObject mixed = new JSONObject(plaintextJson.get("mixed").toString());
                    JSONArray items = mixed.getJSONArray("item");
                    for (int j = 0; j < items.size(); j++) {
                        JSONObject item = new JSONObject(items.get(j).toString());
                        JSONObject content = new JSONObject(item.getStr("content"));
                        String type = item.getStr("type");
                        if ("text".equals(type)) {
                            item.put("content", content.getStr("content"));
                        } else {
                            String url = pullMediaFiles(sdk, type, content);
                            item.put("content", url);
                        }
                        array.put(item);
                    }
                    JSONObject content = new JSONObject();
                    content.put(msgtype, array.toString());
                    plaintextJson.put(msgtype, content.toString());
                } else {
                    pullMediaFiles(sdk, msgtype, plaintextJson);
                }
                // 会话内容写入数据库
                System.out.println(plaintextJson);
                // save(plaintextJson);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    // 拉去媒体信息
    private static String pullMediaFiles(long sdk, String msgtype, JSONObject plaintextJson) {
        String[] msgtypeStr = {"image", "voice", "video", "emotion", "file"};
        List<String> msgtypeList = Arrays.asList(msgtypeStr);
        if (msgtypeList.contains(msgtype)) {
            String savefileName = "";
            JSONObject file = new JSONObject();
            if (!plaintextJson.isNull("msgid")) {
                file = plaintextJson.getJSONObject(msgtype);
                savefileName = plaintextJson.getStr("msgid");
            } else {
                // 混合消息
                file = plaintextJson;
                savefileName = file.getStr("md5sum");
            }
            System.out.println("媒体文件信息：" + file);

            /* ============ 文件存储目录及文件名 Start ============ */
            String suffix = "";
            switch (msgtype) {
                case "image":
                    suffix = ".jpg";
                    break;
                case "voice":
                    suffix = ".amr";
                    break;
                case "video":
                    suffix = ".mp4";
                    break;
                case "emotion":
                    int type = (int) file.get("type");
                    if (type == 1) {
                        suffix = ".gif";
                    } else if (type == 2) {
                        suffix = ".png";
                    }
                    break;
                case "file":
                    suffix = "." + file.getStr("fileext");
                    break;
            }
            savefileName += suffix;
            String path = "/var/data/workwx/";
            String savefile = path + savefileName;
            File targetFile = new File(savefile);
            if (!targetFile.getParentFile().exists())
            //创建父级文件路径
            {
                targetFile.getParentFile().mkdirs();
            }
            /* ============ 文件存储目录及文件名 End ============ */

            /* ============ 拉去文件 Start ============ */
            int i = 0;
            boolean isSave = true;
            String indexbuf = "", sdkfileid = file.getStr("sdkfileid");
            while (true) {
                long mediaData = Finance.NewMediaData();
                int ret = Finance.GetMediaData(sdk, indexbuf, sdkfileid, null, null, 3, mediaData);
                if (ret != 0) {
                    System.out.println("getmediadata ret:" + ret);
                    Finance.FreeMediaData(mediaData);
                    return null;
                }
                System.out.printf("getmediadata outindex len:%d, data_len:%d, is_finis:%d\n",
                        Finance.GetIndexLen(mediaData), Finance.GetDataLen(mediaData),
                        Finance.IsMediaDataFinish(mediaData));
                try {
                    // 大于512k的文件会分片拉取，此处需要使用追加写，避免后面的分片覆盖之前的数据。
                    FileOutputStream outputStream = new FileOutputStream(new File(savefile), true);
                    outputStream.write(Finance.GetData(mediaData));
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (Finance.IsMediaDataFinish(mediaData) == 1) {
                    // 已经拉取完成最后一个分片
                    Finance.FreeMediaData(mediaData);
                    break;
                } else {
                    // 获取下次拉取需要使用的indexbuf
                    indexbuf = Finance.GetOutIndexBuf(mediaData);
                    Finance.FreeMediaData(mediaData);
                }
                // 若文件大于50M则不保存
                if (++i > 100) {
                    isSave = false;
                    break;
                }
            }
            /* ============ 拉去文件 End ============ */
            if (isSave) {
                file.put("sdkfileid", savefile);
                return savefile;
            }
        }
        return null;
    }


}