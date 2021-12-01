package demos;

import com.micro.service.driven.commons.json.gson.GsonBuilders;
import demo.common.CopyUtils;
import dao.MaterialNewsItem;
import dao.NewsItem;
import demo.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zml
 * @createDate 2021-07-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CopyUtilsTest {


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


}
