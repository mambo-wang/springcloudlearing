package com.h3c.servicefeign.web;

import com.h3c.servicefeign.entity.User;
import com.h3c.servicefeign.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name){

        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @GetMapping(value = "/user/{id}")
    public User sayHi(@PathVariable Long id){

        return schedualServiceHi.findById(id);
    }
}