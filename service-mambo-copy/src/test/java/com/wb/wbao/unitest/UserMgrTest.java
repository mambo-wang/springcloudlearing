package com.wb.wbao.unitest;

import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMgrTest {

    @Resource
    private UserMgr userMgr;

    @Test
    public void getUser() {
        User user = userMgr.queryUserById(1L);
        Assert.assertEquals(user.getLoginName(), "admin");
    }

    @Test
    public void createUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setLoginName("123");
        userDTO.setPassword("123");
        userDTO.setUsername("哈哈哈");
        userMgr.createUser(userDTO);
    }

    @Test
    public void removeUser(){
        userMgr.removeUsers(Arrays.asList(23L));
    }
}
