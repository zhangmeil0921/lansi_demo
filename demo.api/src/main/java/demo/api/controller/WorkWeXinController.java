package demo.api.controller;

import demo.common.work.wexin.FinanceDemo;
import demo.web.service.WorkWeXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import request.WorkWexinEntranceReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/2 1:22 下午
 */
@RestController
@RequestMapping("/api/weixin")
public class WorkWeXinController {

    @Autowired
    private FinanceDemo financeDemo;

    @Autowired
    private WorkWeXService workWeXinService;

    @GetMapping("/getMessage")
    public void getMessage(){
        System.out.println("coming--->");
        financeDemo.demo();
    }

    /**
     * 微信公众号入口
     *
     * @param req      请求参数
     * @param request  req
     * @param response res
     */
    @RequestMapping("entrance")
    public void entrance(WorkWexinEntranceReq req, HttpServletRequest request, HttpServletResponse response) {
        req.setRequest(request);
        req.setResponse(response);
        workWeXinService.entrance(req);
    }

}
