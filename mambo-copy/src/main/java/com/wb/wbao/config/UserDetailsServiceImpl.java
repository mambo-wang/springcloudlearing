package com.wb.wbao.config;
import com.wb.wbao.dto.UserDTO;
import com.wb.wbao.service.ZuulClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zhaoxinguo on 2017/9/13.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private ZuulClient zuulClient;

    /**
     * 通过构造器注入UserRepository
     * @param zuulClient
     */
    public UserDetailsServiceImpl(ZuulClient zuulClient) {
        this.zuulClient = zuulClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDTO user = zuulClient.queryByLoginName(username);
//        if(user == null){
//            throw new UsernameNotFoundException(username);
//        }
        UserDTO user = new UserDTO();
        user.setLoginName("admin");
        user.setPassword("1q2w3e");
        user.setRoles(Arrays.asList("ADMIN"));
        return new org.springframework.security.core.userdetails.User(
                user.getLoginName(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> (GrantedAuthority) () -> role)
                        .collect(Collectors.toSet())
        );
    }

}
