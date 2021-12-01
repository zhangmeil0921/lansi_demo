package demo.web.service;

import com.micro.service.driven.commons.entity.ItemResp;
import com.micro.service.driven.commons.entity.Operating;
import com.micro.service.driven.commons.entity.SetsResp;
import com.micro.service.driven.wechat.mp.entity.response.PermanentNewsMaterialListResp;
import dao.MaterialNewsItem;

/**
 * @author Zml
 * @createDate 2021-07-20
 */
public interface MaterialSourceService {

    Operating saveMaterialSource();

    ItemResp<PermanentNewsMaterialListResp> getMaterialSource();

    SetsResp<MaterialNewsItem> getMaterialList(Integer size, Integer current);
}
