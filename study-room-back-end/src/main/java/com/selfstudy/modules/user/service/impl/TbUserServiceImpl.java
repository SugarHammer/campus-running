package com.selfstudy.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.user.dao.TbUserDao;
import com.selfstudy.modules.user.dto.query.TbUserQueryDTO;
import com.selfstudy.modules.user.entity.TbUserEntity;
import com.selfstudy.modules.user.service.TbUserService;
import com.selfstudy.modules.user.vo.TbUserVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUserEntity> implements TbUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbUserEntity> page = this.page(
                new Query<TbUserEntity>().getPage(params),
                new QueryWrapper<TbUserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageResult<TbUserVO> queryByHelper(TbUserQueryDTO queryDTO) {
        Page<TbUserEntity> page = queryDTO.defaultIPageHelper();
        List<TbUserVO> list = baseMapper.queryByHelper(queryDTO);
        return new PageResult<>(list, page.getTotal(), queryDTO);
    }

    @Override
    public List<TbUserEntity> listForBan() {
        List<TbUserEntity> vo = this.baseMapper.listForBan();
        return vo;
    }

}