package com.selfstudy.modules.bas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.bas.dao.BasFloorDao;
import com.selfstudy.modules.bas.entity.BasFloorEntity;
import com.selfstudy.modules.bas.service.BasFloorService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("basFloorService")
public class BasFloorServiceImpl extends ServiceImpl<BasFloorDao, BasFloorEntity> implements BasFloorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasFloorEntity> page = this.page(
                new Query<BasFloorEntity>().getPage(params),
                new QueryWrapper<BasFloorEntity>()
        );

        return new PageUtils(page);
    }

}