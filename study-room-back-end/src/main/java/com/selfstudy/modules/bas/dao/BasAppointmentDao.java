package com.selfstudy.modules.bas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.modules.applet.vo.BasAppointmentVO;
import com.selfstudy.modules.bas.dto.query.BasAppointmentQueryDTO;
import com.selfstudy.modules.bas.entity.BasAppointmentEntity;
import com.selfstudy.modules.bas.entity.BasNoticeEntity;
import com.selfstudy.modules.bas.vo.CountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 预约信息表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@Mapper
public interface BasAppointmentDao extends BaseMapper<BasAppointmentEntity> {

    List<BasAppointmentEntity> queryByHelper(@Param("queryDTO") BasAppointmentQueryDTO queryDTO);

    List<CountVO> countAll(@Param("date") Date date);

    List<BasAppointmentVO> myAppointment(Long userId);

    String getSeatDay(Long id);
}
