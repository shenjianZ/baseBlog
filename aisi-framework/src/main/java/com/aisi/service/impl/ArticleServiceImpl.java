package com.aisi.service.impl;

import com.aisi.constants.SystemConstants;
import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Article;
import com.aisi.domain.entity.Category;
import com.aisi.domain.vo.ArticleListVo;
import com.aisi.domain.vo.HotArticleVo;
import com.aisi.domain.vo.PageVo;
import com.aisi.mapper.ArticleMapper;
import com.aisi.service.ArticleService;
import com.aisi.service.CategoryService;
import com.aisi.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 8:51
 * @Description:
 */

@Service("ArticleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private CategoryService categoryService;
    /**
     * 查询热门文章
     * 查询状态为0的文章(正式发布)
     * 查询文章访问量最高的10条
     *
     * @return 热门文章
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 查询状态为0的文章(正式发布)
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 查询文章访问量最高的10条
        queryWrapper.orderByDesc(Article::getViewCount);
        // top10 文章
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        // 返回结果
//        List<HotArticleVo> hotArticleVos =new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo hotArticlevo = new HotArticleVo();
//            BeanUtils.copyProperties(article, hotArticlevo);
//            hotArticleVos.add(hotArticlevo);
//        }
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVos);
    }

    /**
     * 查询文章列表
     * categoryId为null时，查询所有文章，否则 查询分类id为categoryId的文章
     * 查询状态为0的文章(正式发布)
     * 分页查询
     *
     * @param categoryId 分类id
     * @param pageNum    页码
     * @param pageSize   每页条数
     * @return 文章列表
     */
    @Override
    public ResponseResult articleList(Long categoryId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 查询状态为0的文章(正式发布)
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //　根据is_top 降序
        queryWrapper.orderByDesc(Article::getIsTop);
        if (Objects.nonNull(categoryId) && categoryId > 0) {
            // 查询分类id为categoryId的文章
            queryWrapper.eq(Article::getCategoryId, categoryId);
        }
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 查询结果
        List<Article> articles = page.getRecords();
        // 查询categoryName
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            if (Objects.nonNull(category)) {
//                article.setCategoryName(category.getName());
//            }
//        }
        // 根据categoryId查询categoryName 优化
        articles.stream()
                .map(article ->  article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        // 封装结果 为vo
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        Long total = page.getTotal();
        PageVo pageVo = new PageVo(articleListVos, total);
        return ResponseResult.okResult(pageVo);
    }

}


