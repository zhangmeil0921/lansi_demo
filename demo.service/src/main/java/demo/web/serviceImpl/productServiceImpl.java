package demo.web.serviceImpl;

import com.micro.service.driven.commons.entity.Operating;
import dao.ProductReq;
import demo.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Zml
 * @createDate 2021-07-05
 */
@Service
@RequiredArgsConstructor
public class productServiceImpl implements ProductService {

    @Override
    public Operating addProduct(ProductReq productDo) {
        return null;
    }

    @Override
    public Operating modifyProduct(ProductReq productDo) {
        return null;
    }
}
