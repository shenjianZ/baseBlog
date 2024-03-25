package com.aisi.controller;

import com.aisi.domain.ResponseResult;
import com.aisi.service.LinkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/24 21:18
 * @Description:
 */

@RestController
@RequestMapping("/link")
public class LinkController {
    @Resource
    private LinkService linkService;

    @RequestMapping("/getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }

}
