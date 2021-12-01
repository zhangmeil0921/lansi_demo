package demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dao.SFunctionDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionMapper extends BaseMapper {


  @Select("<script>" +
          " select f.function_id,function_name,path,rfo.role_id from s_account a " +
          " left join s_role_function_operate rfo on rfo.role_id = a.role_id  " +
          " left join s_function f on f.function_id = rfo.function_id " +
          "where account_id in (#{userId}) " +
          "</script>")
  List<SFunctionDO> selectFunctionLists(@Param("userId") String userId);

  @Select("<script> " +
          "select sfo.operate_id operate_id,operate_code,operate_name,sofo.operate_id main_operate_id " +
          "from s_role_function_operate sofo " +
          "left join s_function_operate  sfo on sofo.function_id =sfo.function_id " +
          "where sofo.role_id =#{roleId} and sofo.function_id = #{functionId}  " +
          "</script>")
  List<SOperateDO> selectRoleFunctionOperateDOLists(@Param("functionId") Integer functionId,@Param("roleId") Integer roleId);


}
