package com.aisi.service.impl;

import com.aisi.constants.SystemConstants;
import com.aisi.domain.ResponseResult;
import com.aisi.domain.entity.Link;
import com.aisi.domain.vo.LinkVo;
import com.aisi.mapper.LinkMapper;
import com.aisi.service.LinkService;
import com.aisi.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 友链(AsLink)表服务实现类
 *
 * @author shenjianZ
 * @since 2024-03-24 21:19:47
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINk_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}

