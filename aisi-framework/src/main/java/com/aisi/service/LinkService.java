package com.aisi.service;

import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 友链(AsLink)表服务接口
 *
 * @author shenjianZ
 * @since 2024-03-24 21:19:47
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

}

