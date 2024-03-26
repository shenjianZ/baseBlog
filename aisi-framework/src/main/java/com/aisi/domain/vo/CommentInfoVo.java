package com.aisi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/26 14:48
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfoVo {
    List<CommentVo> rows;
    Long total;
}
