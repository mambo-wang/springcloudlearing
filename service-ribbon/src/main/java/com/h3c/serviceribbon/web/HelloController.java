package com.h3c.serviceribbon.web;

import com.h3c.serviceribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }

    @GetMapping("/test")
    public String test() {
        // 将会请求到：http://localhost:8060/，返回结果：{"index":"欢迎来到首页"}
        return this.restTemplate.getForObject("http://microservice-sidecar-node-service/", String.class);
    }
}
