package com.aisi.controller;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Article;
import com.aisi.service.ArticleService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 8:45
 * @Description:
 */

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> listArticle(){
//        return articleService.list() ;
//    }

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        return articleService.hotArticleList() ;
    }
    @GetMapping("/articleList")
    public ResponseResult getArticleList(Long CategoryId, Integer pageNum, Integer pageSize){
        return articleService.articleList(CategoryId, pageNum, pageSize);
    }
}
