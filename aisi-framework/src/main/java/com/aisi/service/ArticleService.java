package com.aisi.service;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 8:49
 * @Description:
 */


public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Long categoryId, Integer pageNum, Integer pageSize);

    ResponseResult getArticleDetail(Long id);

}
