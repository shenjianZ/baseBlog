package com.aisi.service;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Comment)表服务接口
 *
 * @author shenjianZ
 * @since 2024-03-26 13:34:35
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String CommentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

