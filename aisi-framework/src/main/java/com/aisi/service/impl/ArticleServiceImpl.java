package com.aisi.service.impl;

import com.aisi.constants.SystemConstants;
import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Article;
import com.aisi.domain.vo.HotArticleVo;
import com.aisi.mapper.ArticleMapper;
import com.aisi.service.ArticleService;
import com.aisi.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 8:51
 * @Description:
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

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
}
