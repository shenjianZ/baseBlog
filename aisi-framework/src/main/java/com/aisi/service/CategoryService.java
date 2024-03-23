package com.aisi.service;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 分类表(AsCategory)表服务接口
 *
 * @author shenjianZ
 * @since 2024-03-23 14:14:18
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

