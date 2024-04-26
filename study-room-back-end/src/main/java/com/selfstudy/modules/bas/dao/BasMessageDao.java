package com.selfstudy.modules.bas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.modules.bas.dto.query.BasMessageQueryDTO;
import com.selfstudy.modules.bas.entity.BasMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-03 10:56:39
 */
@Mapper
public interface BasMessageDao extends BaseMapper<BasMessageEntity> {

    List<BasMessageEntity> queryByHelper(@Param("queryDTO") BasMessageQueryDTO queryDTO);
}
