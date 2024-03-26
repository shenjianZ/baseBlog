package com.aisi.service.impl;

import com.aisi.constants.SystemConstants;
import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Comment;
import com.aisi.domain.vo.CommentInfoVo;
import com.aisi.domain.vo.CommentVo;
import com.aisi.mapper.CommentMapper;
import com.aisi.service.CommentService;
import com.aisi.service.UserService;
import com.aisi.utils.BeanCopyUtils;
import com.aisi.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author shenjianZ
 * @since 2024-03-26 13:34:35
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private UserService userService;

    /**
     * 根据文章ID、页码和每页大小获取评论列表
     *
     * @param number
     * @param articleId 文章ID
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @return 评论列表
     */
    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemConstants.Article_Comment_Type.equals(commentType),Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, SystemConstants.Comment_ROOT_ID);
        queryWrapper.eq(Comment::getType, commentType);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Comment> records = page.getRecords();
        List<CommentVo> commentVos = tocommentVoList(records);
        // 查询所有跟评论对应的子评论
        for (CommentVo commentVo : commentVos ){
            List<CommentVo> children= getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new CommentInfoVo(commentVos, page.getTotal()));
    }

    /**
     * 添加评论
     *
     * @param comment 评论实体
     * @return 返回操作结果
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        Long userId = SecurityUtils.getUserId();
        // 这里使用了mybatis-plus的自动填充功能，这里暂时不实现
//        comment.setCreateBy(userId);
//        comment.setCreateTime(new Date());
//        comment.setUpdateTime(new Date());
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据评论根ID获取子评论列表
     *
     * @param id 评论根ID
     * @return 子评论列表
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> records = list(queryWrapper);
        return tocommentVoList(records);
    }

    /**
     * 将评论实体列表转化为评论视图对象列表，封装返回
     *
     * @param comment 评论实体列表
     * @return 评论视图对象列表
     */
    private List<CommentVo> tocommentVoList(List<Comment> comment) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comment, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            // 查询用户名
            commentVo.setUsername(userService.getById(commentVo.getCreateBy()).getNickName());
            // 如果是根评论，则显示用户名，否则显示回复目标用户名
            if (!Objects.equals(commentVo.getToCommentId(), SystemConstants.Comment_ROOT_ID)) {
                commentVo.setToCommentUserName(userService.getById(commentVo.getToCommentUserId()).getNickName());
            }
        }
        return commentVos;
    }
}

