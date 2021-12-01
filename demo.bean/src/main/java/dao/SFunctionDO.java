package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SFunctionDO implements Serializable {

    //菜单名称
    private String functionName;
    //菜单Id
    private Integer functionId;
    //角色id
    private Integer roleId;
}
