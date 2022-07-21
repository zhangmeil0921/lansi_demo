package demos;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.micro.service.driven.commons.json.gson.GsonBuilders;
import dao.MaterialNewsItem;
import dao.NewsItem;
import demo.common.CopyUtils;
import demo.dao.mapper.UEstimatedMapper;
import demo.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zml
 * @createDate 2021-07-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CopyUtilsTest {

    @Autowired
    private UEstimatedMapper uEstimatedMapper;

    @Test
    public void copyTest() throws IllegalAccessException, InstantiationException {
        CopyUtils copyUtils = new CopyUtils();
        NewsItem newsItem = new NewsItem();
        newsItem.setAuthor("jjjj");
        newsItem.setContent("something like this");
        newsItem.setDigest("wocao");
        MaterialNewsItem copy = copyUtils.copy(newsItem, NewsItem.class,MaterialNewsItem.class);
        System.out.println(GsonBuilders.create().toJson(copy));
    }


    @Test
    public void test(){
        Integer integer = uEstimatedMapper.selectCount(new QueryWrapper());
        System.out.println(integer);
    }
}
