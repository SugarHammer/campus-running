package com.selfstudy.modules.bas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.common.base.QueryInfoDTO;
import com.selfstudy.modules.bas.dto.query.BasNoticeQueryDTO;
import com.selfstudy.modules.bas.entity.BasNoticeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公告表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
@Mapper
public interface BasNoticeDao extends BaseMapper<BasNoticeEntity> {

    List<BasNoticeEntity> queryByHelper(@Param("basNoticeQueryDTO") BasNoticeQueryDTO basNoticeQueryDTO);

    List<BasNoticeEntity> listNotice(@Param("queryInfoDTO") QueryInfoDTO queryInfoDTO);
}
