package com.aisi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/24 21:29
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {
    private Long id;
    private String name;
    //logo
    private String logo;
    // 地址
    private String address;
    //描述
    private String description;

}
