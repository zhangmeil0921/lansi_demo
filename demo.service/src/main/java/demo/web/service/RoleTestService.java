package demo.web.service;


import com.micro.service.driven.commons.entity.Operating;
import dao.SFunctionDO;
import dao.SRoleFunctionOperateDO;

import java.util.List;

public interface RoleTestService {
    /**
     * 通过用户ID获取该用户下所有的菜单集合
     * @param userId
     * @return
     */
    List<SFunctionDO> getFunctionLists(String userId);

    /**
     *  通过菜单ID和角色Id获取该角色该菜单下的所有按钮集合
     * @param functionId
     * @param roleId
     * @return
     */
    List<SRoleFunctionOperateDO> getFunctionOperateLists(Integer functionId, Integer roleId);

    Operating addFunction(String userId, Integer functionId);
}
