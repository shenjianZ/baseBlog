package com.aisi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 10:20
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {
    private Long id;
    private String title;
    private Long viewCount;
}
