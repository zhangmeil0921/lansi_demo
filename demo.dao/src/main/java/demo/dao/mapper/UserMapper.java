package demo.dao.mapper;

import mapstruct.User;
import mapstruct.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Zml
 * @createDate 2021-09-06
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source="id",target = "id"),
            @Mapping(source="name",target = "name"),
            @Mapping(source="address",target = "address"),
            @Mapping(source="birth",target = "birth")
    })
    UserDto userToUserDto(User user);
   //集合
    List<UserDto> userToUserDto(List<User> users);
}
