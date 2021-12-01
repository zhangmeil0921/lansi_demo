package demo.api.controller;

import com.micro.service.driven.commons.json.gson.GsonBuilders;
import dao.SFunctionDO;
import dao.SRoleFunctionOperateDO;
import demo.web.service.RoleTestService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/customer")
public class RoleTestController {

    private final RoleTestService roleTestService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/getFunction")
    public List<SFunctionDO> getFunction(String userId){


       List<SFunctionDO> sFunctionDOList =  roleTestService.getFunctionLists(userId);

        logger.info(GsonBuilders.create().toJson(sFunctionDOList));
        return sFunctionDOList;
    }

    @GetMapping("/getFunctionOperation")
    public List<SRoleFunctionOperateDO> getFunctionOperation(Integer functionId, Integer roleId){


        List<SRoleFunctionOperateDO> sFunctionOperateDOList =  roleTestService.getFunctionOperateLists(functionId,roleId);


        return sFunctionOperateDOList;
    }
}
