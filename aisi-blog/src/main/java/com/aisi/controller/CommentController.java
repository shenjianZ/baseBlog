package com.aisi.controller;

import com.aisi.constants.SystemConstants;
import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Comment;
import com.aisi.enums.AppHttpCodeEnum;
import com.aisi.exception.AiSiException;
import com.aisi.service.CommentService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/26 13:36
 * @Description:
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum , Integer pageSize){
        return commentService.commentList(SystemConstants.Article_Comment_Type ,articleId,pageNum, pageSize);
    }

    @PostMapping("")
    public ResponseResult addComment(@RequestBody Comment comment){
        if(!StringUtils.hasText(comment.getContent())){
            throw new AiSiException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        return commentService.addComment(comment);
    }

    @GetMapping("linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum , Integer pageSize){
        return commentService.commentList(SystemConstants.Link_Comment_Type ,null,pageNum, pageSize);
    }
}
