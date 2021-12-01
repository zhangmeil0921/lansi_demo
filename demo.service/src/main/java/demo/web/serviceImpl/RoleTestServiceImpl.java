package demo.web.serviceImpl;

import com.micro.service.driven.commons.entity.Operating;
import dao.SFunctionDO;
import dao.SRoleFunctionOperateDO;
import demo.dao.mapper.FunctionMapper;
import demo.dao.mapper.SOperateDO;
import demo.web.service.RoleTestService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleTestServiceImpl implements RoleTestService {

    private final FunctionMapper functionMapper;

    @Cacheable(value = "function",key = "#userId",condition = "#userId>1",unless = "#userId==null")
    @Override
    public List<SFunctionDO> getFunctionLists(String userId) {
       List<SFunctionDO> sFunctionDOList =  functionMapper.selectFunctionLists(userId);
        return sFunctionDOList;
    }

    @Override
    public List<SRoleFunctionOperateDO> getFunctionOperateLists(Integer functionId, Integer roleId) {
        List<SRoleFunctionOperateDO> sRoleFunctionOperateDOS = new ArrayList<>();
        List<SOperateDO> sOperateDOList =   functionMapper.selectRoleFunctionOperateDOLists(functionId,roleId);
        if (sOperateDOList.size()>0){
            String operateIds =  sOperateDOList.get(0).getMainOperateId();

            if(StringUtils.isNoneEmpty(operateIds) ){
                String[] operateIDs = operateIds.split(",");
                sOperateDOList.stream().forEach(ItemDO->{
                    SRoleFunctionOperateDO dos = new SRoleFunctionOperateDO();
                    dos.setOperateCode(ItemDO.getOperateCode());
                    dos.setOperateName(ItemDO.getOperateName());
                    for (String id : operateIDs){
                        if (id.equals(ItemDO.getOperateId())){
                            dos.setIsShow(true);
                            break;
                        }else{
                            dos.setIsShow(false);
                        }
                    }
                    sRoleFunctionOperateDOS.add(dos);
                });
            }
        }
        return sRoleFunctionOperateDOS;
    }

    @Override
    public Operating addFunction(String userId, Integer functionId) {
        return null;
    }

}
