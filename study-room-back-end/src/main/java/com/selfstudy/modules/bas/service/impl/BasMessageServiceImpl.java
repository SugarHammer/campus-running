package com.selfstudy.modules.bas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.bas.dao.BasMessageDao;
import com.selfstudy.modules.bas.dto.query.BasMessageQueryDTO;
import com.selfstudy.modules.bas.entity.BasMessageEntity;
import com.selfstudy.modules.bas.service.BasMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("basMessageService")
public class BasMessageServiceImpl extends ServiceImpl<BasMessageDao, BasMessageEntity> implements BasMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasMessageEntity> page = this.page(
                new Query<BasMessageEntity>().getPage(params),
                new QueryWrapper<BasMessageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageResult<BasMessageEntity> queryByHelper(BasMessageQueryDTO queryDTO) {
        Page<BasMessageEntity> page = queryDTO.defaultIPageHelper();
        List<BasMessageEntity> list = baseMapper.queryByHelper(queryDTO);
        return new PageResult<>(list, page.getTotal(), queryDTO);
    }

}