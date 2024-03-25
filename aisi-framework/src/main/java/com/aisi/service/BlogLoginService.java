package com.aisi.service;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.User;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/24 22:04
 * @Description:
 */


public interface BlogLoginService {
    ResponseResult login(User loginUser);

}
