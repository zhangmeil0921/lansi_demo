package demos;

import com.micro.service.driven.commons.json.gson.GsonBuilders;
import com.micro.service.driven.qiniu.entity.request.UploadLocalFileReq;
import com.micro.service.driven.qiniu.entity.request.UploadStreamFileReq;
import com.micro.service.driven.qiniu.entity.response.UploadFileResp;
import com.micro.service.driven.qiniu.service.QiNiuTemplate;
import com.qiniu.common.Zone;
import demo.common.qiniu.CredentialsManager;
import demo.common.qiniu.FetchQiNiu;
import demo.common.qiniu.OperateQiNiu;
import demo.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static demo.common.qiniu.OperateQiNiu.getAuth;

/**
 * 七牛云的测试类
 * @author Zml
 * @createDate 2021-07-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class QiNiuTest {

    @Autowired
    OperateQiNiu operateQiNiu;

    @Autowired
    FetchQiNiu fetchQiNiu;

    @Autowired
    CredentialsManager credentialsManager;

    @Autowired
    QiNiuTemplate qiNiuTemplate;

    /**
     * key:文件名
     * fromBucket:源存储空间
     * toBucket: 目标存储空间
     * fromKey:源文件名
     * toKey:目标文件名
     *
     */
    @Test
    public void operateTest(){
        //        editFileType(Zone.zone0(),"",getAuth(),"store-demo2","doc");
//        delete(Zone.zone0(), "Fq8KWehX83XY29Ct1WNNDF_63IRv", getAuth(), bucket);
//        move(Zone.zone0(),getAuth(),"store-demo","Fu3P9AgpOo_0q_4KnJdn9ZXVimfD","store-demo2","new");
//        copy(Zone.zone0(),getAuth(),"store-demo","Fu3P9AgpOo_0q_4KnJdn9ZXVimfD","store-demo2","=");
//        getFileList(Zone.zone0(),getAuth(),"store-demo2","",10,"");
        operateQiNiu.fetchToSpace(Zone.zone0(),getAuth(),"store-demo","aaa/cute","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202005%2F28%2F20200528155430_qdpsv.thumb.400_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1627548830&t=631c655ce98f9740f53bc88d6d80df01");

    }

    @Test
    public void fetchTest(){
        //        publicFile("FmMExqZxgW7IqHUCW7d67P_5nM_Y","qvgbzwpu7.hd-bkt.clouddn.com");
        //privateFile(CredentialsManager.getAuth(),"文件名","你的domainOfBucket",3600);
//        getFileIns输入、、one.zone0(),"FpYcEiadqxpBaJopVWFhlJDbqTbO",getAuth(),bucket);
//        getUploadCredential();
        fetchQiNiu.getOverloadCredential("video/mp4");
    }

    @Test
    public void pictureUploadTest(){
        //        fileUpload(Zone.zone0(),CredentialsManager.getUploadCredential(),"/Users/lansi/picture/5.jpeg");
//        charArrayUpload(Zone.zone0(),getUploadCredential());
//        streamUpload(Zone.zone0(),getUploadCredential());
        credentialsManager.breakPointUpload(Zone.zone0(), CredentialsManager.getUploadCredential(),"/Users/lansi/picture/2.jpeg");
    }

    @Test
    public void qiNiuTest(){
        UploadLocalFileReq localFileReq = new UploadLocalFileReq();
        localFileReq.setLocalFilePath("/Users/lansi/Movies/Videos/Media.localized/星空.mp4");
        localFileReq.setMime("星空");
//        localFileReq.setBucket("test1");
//        localFileReq.setQiNiuRegion(RegionEnum.HuaDong);
//        localFileReq.setKey("PPi8GMBRuyeVWvmH0zTsM7WDrBz97qWeo9puAHld");
        UploadFileResp uploadFileResp = qiNiuTemplate.uploadLocalFile(localFileReq);
        System.out.println(GsonBuilders.create().toJson(uploadFileResp));
    }

    @Test
    public void qiNiuUploadVideoTest() throws FileNotFoundException {
        UploadStreamFileReq req = new UploadStreamFileReq();
        req.setInputStream(new FileInputStream("/Users/lansi/Movies/Videos/Media.localized/1631873418781292.mp4"));
        req.setKey("long.mp4");
        UploadFileResp uploadFileResp = qiNiuTemplate.uploadStreamFile(req);
        System.out.println(GsonBuilders.create().toJson(uploadFileResp));
    }

    @Test
    public void getVideoTest(){
        String publicFile = qiNiuTemplate.getPublicFile("start.mp4");
        System.out.println(publicFile);
    }
}
