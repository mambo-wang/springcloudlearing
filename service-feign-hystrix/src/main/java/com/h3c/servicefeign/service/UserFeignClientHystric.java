package com.h3c.servicefeign.service;

import com.h3c.servicefeign.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientHystric implements UserFeignClient {

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setUsername("default");
        return user;
    }
}
