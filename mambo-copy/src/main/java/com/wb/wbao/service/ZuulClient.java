package com.wb.wbao.service;

import com.wb.wbao.dto.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dell on 2018/3/4.
 */
@FeignClient(value = "microservice-gateway-zuul")
public interface ZuulClient {

    @GetMapping(value = "/user/users")
    UserDTO queryByLoginName(@RequestParam(value = "login_name") String loginName);




}
