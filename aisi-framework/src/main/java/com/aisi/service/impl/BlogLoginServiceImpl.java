package com.aisi.service.impl;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.LoginUser;
import com.aisi.domain.entity.User;
import com.aisi.domain.vo.BlogUserLoginVo;
import com.aisi.domain.vo.UserInfoVo;
import com.aisi.service.BlogLoginService;
import com.aisi.utils.BeanCopyUtils;
import com.aisi.utils.JwtUtil;
import com.aisi.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/24 22:05
 * @Description:
 */

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User loginUser) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser1 = (LoginUser) authenticate.getPrincipal();
        String userId = String.valueOf(loginUser1.getUser().getId());
        String jwt = JwtUtil.createJWT(userId, JwtUtil.JWT_TTL);
        redisCache.setCacheObject("bloglogin:" + userId, loginUser1);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser1, UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        // 获取token。来解析获取用户id
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = String.valueOf(loginUser.getUser().getId());
        redisCache.getCacheObject("bloglogin:" + userId);
        // 删除redis中的token
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult();
    }
}
