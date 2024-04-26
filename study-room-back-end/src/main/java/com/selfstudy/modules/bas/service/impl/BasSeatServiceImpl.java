package com.selfstudy.modules.bas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.bas.dao.BasSeatDao;
import com.selfstudy.modules.bas.dto.query.BasSeatQueryDTO;
import com.selfstudy.modules.bas.entity.BasSeatEntity;
import com.selfstudy.modules.bas.service.BasSeatService;
import com.selfstudy.modules.bas.vo.BasSeatVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("basSeatService")
public class BasSeatServiceImpl extends ServiceImpl<BasSeatDao, BasSeatEntity> implements BasSeatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasSeatEntity> page = this.page(
                new Query<BasSeatEntity>().getPage(params),
                new QueryWrapper<BasSeatEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageResult<BasSeatVO> queryByHelper(BasSeatQueryDTO queryDTO) {
        Page<BasSeatEntity> page = queryDTO.defaultIPageHelper();
        List<BasSeatVO> list = baseMapper.queryByHelper(queryDTO);
        return new PageResult<>(list, page.getTotal(), queryDTO);
    }

    @Override
    public List<BasSeatEntity> getSeatByRoom(Long id) {
        LambdaQueryWrapper<BasSeatEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BasSeatEntity::getRoomId,id);
        wrapper.orderByAsc(BasSeatEntity::getCreateTime);
        List<BasSeatEntity> basSeatEntities = baseMapper.selectList(wrapper);
        return basSeatEntities;
    }

    @Override
    public List<Long> getSeatID(List<Long> asList) {
        List<Long> seatIDs = baseMapper.getSeatID(asList);
        return seatIDs;
    }


}