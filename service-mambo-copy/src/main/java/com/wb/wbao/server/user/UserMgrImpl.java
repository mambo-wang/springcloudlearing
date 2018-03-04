package com.wb.wbao.server.user;

import com.h3c.common.utils.FileUtils;
import com.h3c.common.concurrent.MamboExecutors;
import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.server.role.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service("userMgr")
public class UserMgrImpl implements UserMgr {

    @Resource
    private UserDao userDao;

    private Logger logger = LoggerFactory.getLogger(UserMgrImpl.class);


    /**
     * 检测文件变化
     */
    @PostConstruct
    private void init() {
        MamboExecutors.get().getIoBoundService().submit(this::watchFileChange);
    }

    private void watchFileChange(){

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Paths.get("C:\\filetest").register(watchService,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent watchEvent : key.pollEvents()) {
                    System.out.println(watchEvent.context() + "文件发生了" + watchEvent.kind() + "事件");
                    this.queryAll();
                }

                boolean valid = key.reset();
                if(!valid){
                    break;
                }

                TimeUnit.SECONDS.sleep(2);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<UserDTO> queryAll() {
        logger.debug("query all user");
        List<UserDTO> userDTOS = userDao.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        userDTOS.forEach(userDTO -> FileUtils.checkUserDir(userDTO.getId()));

        return userDTOS;
    }

    @Override
    public User queryUserById(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = this.convertToUser(userDTO);
        User userDB =  userDao.save(user);
        FileUtils.checkUserDir(userDB.getId());
        return userDB;
    }

    @Override
    public User modifyUser(UserDTO userDTO) {
        User user = this.convertToUser(userDTO);
        return userDao.save(user);
    }

    @Override
    public void removeUsers(List<Long> userIds) {
        userIds.forEach(this::deleteUser);
    }

    private void deleteUser(Long userId){
        FileUtils.deleteUserDir(userId);
        userDao.delete(userId);
    }

    @Override
    public User queryUserByLoginNameAndPassword(String loginName, String password) {
        return userDao.findByLoginNameAndPassword(loginName, password);
    }

    @Override
    public User queryByLoginName(String loginName) {

        logger.info("query by loginName");
        return userDao.findByLoginName(loginName);
    }

    @Override
    public UserDTO convertToDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLoginName(user.getLoginName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoleList().stream().map(Role::getRole).collect(Collectors.toList()));
        return userDTO;
    }

    private String getRoleStr(List<Role> roleList) {
        return roleList.stream()
                .map(Role::getRole)
                .collect(Collectors.joining(","));
    }

    @Override
    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        if(Objects.nonNull(userDTO.getId())){
            user = this.queryUserById(userDTO.getId());
        }
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setLoginName(userDTO.getLoginName());
        user.setRoleList(this.getRole(userDTO));
        return user;
    }

    private List<Role> getRole(UserDTO userDTO) {

        List<Role> roles = new ArrayList<>();

        if(userDTO.getRoles().contains(Role.ROLE_ADMIN)){
            Role role = new Role();
            role.setId(1L);
            roles.add(role);
        }

        if(userDTO.getRoles().contains(Role.ROLE_USER)){
            Role role = new Role();
            role.setId(2L);
            roles.add(role);
        }
        return roles;
    }


    @Override
    public Set<String> queryRoles(String loginName) {
        return this.queryByLoginName(loginName).getRoleList()
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = this.queryUserById(userId);
        user.setPassword(newPassword);
        userDao.save(user);
    }

    @Override
    public String login(String loginName, String password) {

        User user = userDao.findByLoginName(loginName);
        if(Objects.nonNull(user) && StringUtils.equals(password, user.getPassword())){
            return "Bearer " + Jwts.builder()
                    .setSubject(user.getLoginName())
                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .signWith(SignatureAlgorithm.HS512, "Mambo") //采用什么算法是可以自己选择的，不一定非要采用HS512
                    .compact();
        }

        return "";
    }
}
