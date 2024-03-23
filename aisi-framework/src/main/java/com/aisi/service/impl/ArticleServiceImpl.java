package com.aisi.service.impl;

import com.aisi.domain.entity.Article;
import com.aisi.mapper.ArticleMapper;
import com.aisi.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: shenjianZ
 * @Date: 2024/3/23 8:51
 * @Description:
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
