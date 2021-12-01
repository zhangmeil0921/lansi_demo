package demo.api.controller;

import com.micro.service.driven.commons.entity.Operating;
import com.micro.service.driven.commons.entity.SetsResp;
import dao.MaterialNewsItem;
import dao.PageReq;
import demo.web.service.MaterialSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 素材controller
 * @author Zml
 * @createDate 2021-07-29
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/material")
public class MaterialController {


    private  final MaterialSourceService materialSourceService;


    @GetMapping("/getMaterialList")
    public SetsResp<MaterialNewsItem> getMaterialList(@Validated PageReq req) {
        return  materialSourceService.getMaterialList(req.getLimit() ,req.getOffset());
    }

    @GetMapping("/saveMaterial")
    public Operating saveMaterial(){
        Operating operating = materialSourceService.saveMaterialSource();
        return operating;
    }
}
