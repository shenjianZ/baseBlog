package com.aisi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/24 22:42
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {
    private String token;
    private UserInfoVo userInfo;
}
