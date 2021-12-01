package demo.web.service;

import com.micro.service.driven.commons.entity.Operating;
import dao.ProductReq;

/**
 * @author Zml
 * @createDate 2021-07-05
 */
public interface ProductService {


    Operating addProduct(ProductReq productDo);

    Operating modifyProduct(ProductReq productDo);
}
