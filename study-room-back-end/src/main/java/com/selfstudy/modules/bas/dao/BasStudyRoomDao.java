package com.selfstudy.modules.bas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.modules.bas.dto.query.BasStudyRoomQueryDTO;
import com.selfstudy.modules.bas.entity.BasStudyRoomEntity;
import com.selfstudy.modules.bas.vo.BasStudyRoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自习室表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@Mapper
public interface BasStudyRoomDao extends BaseMapper<BasStudyRoomEntity> {

    List<BasStudyRoomVO> queryByHelper(@Param("queryDTO") BasStudyRoomQueryDTO queryDTO);
}
