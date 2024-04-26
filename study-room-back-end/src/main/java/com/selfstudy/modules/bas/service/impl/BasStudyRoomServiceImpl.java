package com.selfstudy.modules.bas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.bas.dao.BasStudyRoomDao;
import com.selfstudy.modules.bas.dto.query.BasStudyRoomQueryDTO;
import com.selfstudy.modules.bas.entity.BasNoticeEntity;
import com.selfstudy.modules.bas.entity.BasStudyRoomEntity;
import com.selfstudy.modules.bas.service.BasStudyRoomService;
import com.selfstudy.modules.bas.vo.BasStudyRoomVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("basStudyRoomService")
public class BasStudyRoomServiceImpl extends ServiceImpl<BasStudyRoomDao, BasStudyRoomEntity> implements BasStudyRoomService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasStudyRoomEntity> page = this.page(
                new Query<BasStudyRoomEntity>().getPage(params),
                new QueryWrapper<BasStudyRoomEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageResult<BasStudyRoomVO> queryByHelper(BasStudyRoomQueryDTO queryDTO) {
        Page<BasStudyRoomEntity> page = queryDTO.defaultIPageHelper();
        List<BasStudyRoomVO> list = baseMapper.queryByHelper(queryDTO);
        return new PageResult<>(list, page.getTotal(), queryDTO);
    }

    @Override
    public List<BasStudyRoomEntity> getRoomByFloor(Long id) {
        LambdaQueryWrapper<BasStudyRoomEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BasStudyRoomEntity::getFloorId,id);
        List<BasStudyRoomEntity> roomEntities = baseMapper.selectList(wrapper);
        return roomEntities;
    }

}