package com.aisi.service.impl;

import com.aisi.constants.SystemConstants;
import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Article;
import com.aisi.domain.entity.Category;
import com.aisi.domain.vo.CategoryVo;
import com.aisi.mapper.CategoryMapper;
import com.aisi.service.ArticleService;
import com.aisi.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.aisi.service.CategoryService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(AsCategory)表服务实现类
 *
 * @author shenjianZ
 * @since 2024-03-23 14:14:18
 */
@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private ArticleService articleService;

    /**
     * 获取分类列表
     * 通过文章表(正常发布的文章)获取分类列表ID
     * 根据分类ID 去查询分类列表(正常状态的分类)
     * @return 分类列表
     */
    @Override
    public ResponseResult getCategoryList() {
        // 查询文章列表 已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list();
        // 获取文章分类ID 去重
        List<Long> categoryIds = articles.stream()
                .map(Article::getCategoryId)
                .distinct()
                .collect(Collectors.toList());
        // 查询分类列表
//        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
//        categoryWrapper.in(Category::getId, categoryIds);
//        categoryWrapper.eq(Category::getStatus, SystemConstants.Category_STATUS_NORMAL);
//        List<Category> categories = list(categoryWrapper);
        // 查询分类列表
        List<Category> categories = listByIds(categoryIds);
        // 过滤分类列表 stream流 得到状态为0 （正常） 的分类
        categories.stream().filter(category -> SystemConstants.Category_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        // 封装为VO 对象
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

}

