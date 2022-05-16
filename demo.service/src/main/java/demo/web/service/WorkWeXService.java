package demo.web.service;

import request.WorkWexinEntranceReq;

/**
 * @author ZML
 * @version 1.0
 * @date 2021/12/15 5:59 下午
 */
public interface WorkWeXService {

    /**
     * 测试回调
     * @param req
     */
    void entrance(WorkWexinEntranceReq req);
}
