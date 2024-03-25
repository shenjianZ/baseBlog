package com.aisi.controller;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.User;
import com.aisi.service.BlogLoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/24 22:00
 * @Description:
 */

@RestController
@RequestMapping()
public class BlogLoginController {

    @Resource
    private BlogLoginService blogLoginService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User loginUser) {
        return blogLoginService.login(loginUser);
    }



}
