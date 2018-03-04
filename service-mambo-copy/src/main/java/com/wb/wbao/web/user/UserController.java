package com.wb.wbao.web.user;

import com.h3c.common.model.CommonDTO;
import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.server.user.User;
import com.wb.wbao.server.user.UserMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dell on 2017/7/2.
 * 控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserMgr userMgr;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public CommonDTO login(@RequestBody User user){
        String token = userMgr.login(user.getLoginName(), user.getPassword());
        return new CommonDTO(CommonDTO.Result.SUCCESS, "", token);
    }
    @GetMapping
    public UserDTO queryByLoginName(@RequestParam(value = "login_name") String loginName){
        return this.userMgr.convertToDTO(this.userMgr.queryByLoginName(loginName));
    }


    @GetMapping(value = "/{id}")
    public User queryById(@PathVariable Long id){
        return userMgr.queryUserById(id);
    }

    @PostMapping
    public List<UserDTO> createUser(@RequestBody UserDTO userDTO){
        userMgr.createUser(userDTO);
        logger.info("create a user");
        return userMgr.queryAll();
    }

    @PutMapping
    public List<UserDTO> modifyUser(@RequestBody UserDTO userDTO){
        userMgr.modifyUser(userDTO);
        return userMgr.queryAll();
    }

    @DeleteMapping(value = {"/{idList}"})
    public List<UserDTO> removeUsers(@PathVariable List<Long> idList){

        userMgr.removeUsers(idList);
        return userMgr.queryAll();
    }
}
