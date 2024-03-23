package com.aisi.domain.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 15:32
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private List<ArticleListVo> rows;
    private Long total;
}
