package com.aisi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/24 22:43
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoVo {
    private Long id;
    private String avatar;
    private String nickName;
    private String sex;
    private String email;

}
