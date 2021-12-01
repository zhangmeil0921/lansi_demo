package demos;

import demo.dao.mapper.UserMapper;
import mapstruct.User;
import mapstruct.UserDto;
import demo.start.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Zml
 * @createDate 2021-09-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MapperConstructTest {

    @Test
    public void userPoToUserDto() {
        User user =new User();
        user.setId(1);
        user.setName("myx");
        user.setAddress("河北沧州");
        user.setBirth(new Date());
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        System.out.println(userDto);
    }
}
