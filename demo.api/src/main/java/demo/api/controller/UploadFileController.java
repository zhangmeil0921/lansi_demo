package demo.api.controller;

import com.micro.service.driven.commons.entity.FileMimeInfo;
import com.micro.service.driven.commons.json.gson.GsonBuilders;
import com.micro.service.driven.commons.utils.FileUtils;
import com.micro.service.driven.qiniu.entity.request.UploadBytesFileReq;
import com.micro.service.driven.qiniu.entity.response.UploadFileResp;
import com.micro.service.driven.qiniu.service.QiNiuTemplate;
import demo.web.service.filter.QiniuStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 七牛云上传视频接口
 * @author Zml
 * @createDate 2021-07-23
 */
@Controller
@RequestMapping("/video")
public class UploadFileController {

    @Autowired
    QiniuStorageService qiniuStorage;

    @Autowired
    QiNiuTemplate qiNiuTemplate;


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile picture){
        Map<String,Object> map = new HashMap<>();
        try {
            if (null != picture && picture.getBytes().length > 0) {
                UploadBytesFileReq bytesFileReq = new UploadBytesFileReq();
                bytesFileReq.setUploadBytes(picture.getBytes());
//                if(!picture.getOriginalFilename().endsWith(".mp4")){
                    bytesFileReq.setKey("guest.mp4");
//                }
                FileMimeInfo mimeInfo = FileUtils.getFileMimeInfo(picture.getOriginalFilename());
//                bytesFileReq.setKey("");
                UploadFileResp fileResp = qiNiuTemplate.uploadBytesFile(bytesFileReq);
                map.put("key",GsonBuilders.create().toJson(fileResp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GsonBuilders.create().toJson(map);
    }

    @PostMapping("/uploadVedio")
    @ResponseBody
    public String uploadVedio(@RequestParam("file") MultipartFile picture){
        Map<String,Object> map = new HashMap<>();
        try {
            if (null != picture && picture.getBytes().length > 0) {
                String key = qiniuStorage.uploadVideo(picture);
                map.put("key",key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GsonBuilders.create().toJson(map);
    }


}
