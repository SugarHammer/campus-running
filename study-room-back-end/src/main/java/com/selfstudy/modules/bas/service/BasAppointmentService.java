package com.selfstudy.modules.bas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.modules.applet.dto.save.BasAppointmentSaveDTO;
import com.selfstudy.modules.applet.vo.BasAppointmentVO;
import com.selfstudy.modules.bas.dto.query.BasAppointmentQueryDTO;
import com.selfstudy.modules.bas.entity.BasAppointmentEntity;
import com.selfstudy.modules.bas.vo.CountVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约信息表
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
public interface BasAppointmentService extends IService<BasAppointmentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageResult<BasAppointmentEntity> queryByHelper(BasAppointmentQueryDTO queryDTO);

    List<CountVO> countAll(Date date);

    boolean appointment(BasAppointmentSaveDTO saveDTO, Long userId);

    List<BasAppointmentVO> myAppointment(Long userId);

    Boolean cancel(Long id);

    Boolean over(Long id);
}

