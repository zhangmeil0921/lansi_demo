package demo.web.serviceImpl;

import com.micro.service.driven.commons.entity.ItemResp;
import com.micro.service.driven.commons.entity.Operating;
import com.micro.service.driven.commons.entity.SetsResp;
import com.micro.service.driven.commons.json.gson.GsonBuilders;
import com.micro.service.driven.wechat.mp.entity.response.PermanentNewsMaterialListResp;
import com.micro.service.driven.wechat.mp.service.WeChatMpTemplate;
import dao.MaterialNewsItem;
import dao.MaterialSourceDao;
import demo.common.CopyUtils;
import demo.dao.mapper.MaterialSourceMapper;
import demo.web.service.MaterialSourceService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对微信素材操作
 *
 * @author Zml
 * @createDate 2021-07-20
 */
@Service
@RequiredArgsConstructor
public class MaterialSourceServiceImpl implements MaterialSourceService {

    @Autowired
    private  WeChatMpTemplate weChatMpTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MaterialSourceMapper materialSourceMapper;
    /**
     * 乐享屋 定时器拉取素材列表
     *
     * @throws ParseException
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduled() {
        saveMaterialSource();
    }

    @Override
    public Operating saveMaterialSource() {

        ZoneId zone = ZoneId.systemDefault();
        PermanentNewsMaterialListResp materialList = weChatMpTemplate.getPermanentNewsMaterialList();
        List<PermanentNewsMaterialListResp.NewsItem> newsItems = materialList.getNewsItems();
        Boolean saveBatchResult = true;
        List<MaterialSourceDao> newList = new ArrayList<>();
        int count = 500;
        if (null != newsItems) {
            try {
                int listSize = newsItems.size();
                if (newsItems.size() > 0) {
                    for (int i = 0; i < listSize; i++) {
                        MaterialSourceDao sourceDao = copy(zone, newsItems, i);
                        materialSourceMapper.insert(sourceDao);
                    }
                }
            } catch (Exception e) {
                saveBatchResult = false;
            }
        }
        return new Operating(saveBatchResult == true);
    }

    //执行循环遍历转换对象
    private MaterialSourceDao copy(ZoneId zone, List<PermanentNewsMaterialListResp.NewsItem> newsItems, int i) {
        PermanentNewsMaterialListResp.NewsItem newsItem = newsItems.get(i);
        MaterialSourceDao materialSource = new MaterialSourceDao();
        newsItem.getIsNeedOpenComment();
        newsItem.getIsOnlyFansCanComment();
        newsItem.getIsShowCoverPicture();
        materialSource.setContentSourceUrl(newsItem.getContentSourceUrl());
        materialSource.setContent(newsItem.getContent());
        materialSource.setAuthor(newsItem.getAuthor());
        materialSource.setDigest(newsItem.getDigest());
        Long createTime = newsItem.getCreateTime() == null ? null : newsItem.getCreateTime() * 1000;
        Long updateTime = newsItem.getUpdateTime() == null ? null : newsItem.getCreateTime() * 1000;
        LocalDateTime createDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(createTime), zone);
        LocalDateTime updateDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(updateTime), zone);
        materialSource.setCreateTime(createDate);
        materialSource.setMediaId(newsItem.getMediaId());
        materialSource.setUrl(newsItem.getUrl());
        materialSource.setUpdateTime(updateDate);
        materialSource.setTitle(newsItem.getTitle());
        materialSource.setType("news");
        materialSource.setThumbMediaId(newsItem.getThumbMediaId());
        materialSource.setIsOnline(1);
        return materialSource;
    }

    @Override
    public ItemResp<PermanentNewsMaterialListResp> getMaterialSource() {
//        String accessToken = weChatMpTemplate.getAccessToken();
        PermanentNewsMaterialListResp materialList = weChatMpTemplate.getPermanentNewsMaterialList();
        System.out.println("ppp=>" + GsonBuilders.create().toJson(materialList));
        return new ItemResp<>(materialList);
    }

    @Override
    public SetsResp<MaterialNewsItem> getMaterialList(Integer size, Integer current) {
        List<MaterialNewsItem> items = new ArrayList<>();
        try {
            //获取素材列表
            PermanentNewsMaterialListResp materialList = weChatMpTemplate.getPermanentNewsMaterialList();
            List<PermanentNewsMaterialListResp.NewsItem> newsItems = materialList.getNewsItems();
            CopyUtils utils = new CopyUtils();
            newsItems.stream().forEach(newsItem -> {
                try {
                    //将素材列表拷贝到自己的对象
                    MaterialNewsItem material = utils.copy(newsItem, PermanentNewsMaterialListResp.NewsItem.class, MaterialNewsItem.class);
                    items.add(material);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            items.stream().sorted(Comparator.comparing(MaterialNewsItem::getCreateTime)).collect(Collectors.toList());
            //对数据进行分页
            size = ObjectUtils.isEmpty(size) ? 10 : size;
            int currentId = ObjectUtils.isEmpty(current) ? 1 : (current - 1) * size;
            List<MaterialNewsItem> itemList = new ArrayList<>();
//            Page<MaterialNewsItem> page = new Page();

            int totalCount = items.size();
            for (int i = 0; i < size && i < totalCount - currentId; i++) {
                itemList.add(items.get(currentId + i));
            }
//            page.setRowCount((long) itemList.size());
//            page.setList(itemList);
            return new SetsResp<>((long) newsItems.size(), itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
