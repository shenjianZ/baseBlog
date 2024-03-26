package com.aisi.filter;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.LoginUser;
import com.aisi.domain.entity.User;
import com.aisi.enums.AppHttpCodeEnum;
import com.aisi.utils.JwtUtil;
import com.aisi.utils.RedisCache;
import com.aisi.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/26 6:58
 * @Description:
 */

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 没有请求头，不需拦截，放行即可
        if (StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 从redis中查询，,解析出userId
        String userId = null;
        try {
            userId = JwtUtil.parseJWT(token).getSubject();
        } catch (Exception e) {
            // 请求头有token，但是token过期或篡改，则拦截
            // 封装错误信息JSON响应
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 验证token
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userId);
        if (Objects.isNull(loginUser)) {
            // 封装token过期的 JSON响应
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
