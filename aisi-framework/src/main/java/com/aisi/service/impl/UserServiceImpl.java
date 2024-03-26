package com.aisi.service.impl;

import com.aisi.domain.entity.User;
import com.aisi.mapper.UserMapper;
import com.aisi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author shenjianZ
 * @since 2024-03-26 14:00:31
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

