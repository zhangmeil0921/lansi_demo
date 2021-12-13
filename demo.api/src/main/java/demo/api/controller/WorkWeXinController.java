package demo.api.controller;

import demo.common.work.wexin.FinanceDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 1:22 下午
 */
@RequestMapping("api/weixin/")
public class WorkWeXinController {

    @Autowired
    private FinanceDemo financeDemo;

    @GetMapping("getMessage")
    public void getMessage(){
        financeDemo.demo();
    }
}
