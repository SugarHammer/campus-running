package com.selfstudy.modules.bas.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.exception.RRException;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.applet.dto.save.BasAppointmentSaveDTO;
import com.selfstudy.modules.applet.vo.BasAppointmentVO;
import com.selfstudy.modules.bas.dao.BasAppointmentDao;
import com.selfstudy.modules.bas.dao.BasSeatDao;
import com.selfstudy.modules.bas.dto.query.BasAppointmentQueryDTO;
import com.selfstudy.modules.bas.entity.BasAppointmentEntity;
import com.selfstudy.modules.bas.entity.BasSeatEntity;
import com.selfstudy.modules.bas.service.BasAppointmentService;
import com.selfstudy.modules.bas.service.BasSeatService;
import com.selfstudy.modules.bas.vo.CountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("basAppointmentService")
public class BasAppointmentServiceImpl extends ServiceImpl<BasAppointmentDao, BasAppointmentEntity> implements BasAppointmentService {

    @Autowired
    private BasSeatService basSeatService;
    @Autowired
    private BasSeatDao basSeatDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasAppointmentEntity> page = this.page(
                new Query<BasAppointmentEntity>().getPage(params),
                new QueryWrapper<BasAppointmentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageResult<BasAppointmentEntity> queryByHelper(BasAppointmentQueryDTO queryDTO) {
        Page<BasAppointmentEntity> page = queryDTO.defaultIPageHelper();
        List<BasAppointmentEntity> list = baseMapper.queryByHelper(queryDTO);
        return new PageResult<>(list, page.getTotal(), queryDTO);
    }

    @Override
    public List<CountVO> countAll(Date date) {
        return baseMapper.countAll(date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean appointment(BasAppointmentSaveDTO saveDTO, Long userId) {
        boolean update = basSeatService.lambdaUpdate().eq(BasSeatEntity::getSeatId, saveDTO.getSeatId()).set(BasSeatEntity::getRoomState, 1).update();
        BasAppointmentEntity basAppointmentEntity = BeanUtil.copyProperties(saveDTO, BasAppointmentEntity.class);
        basAppointmentEntity.setSeatTime(new Date());
        basAppointmentEntity.setSeatState(1);
        basAppointmentEntity.setCreateUserId(userId);
        basAppointmentEntity.setUserId(userId);
        boolean save = save(basAppointmentEntity);
        if (save && update) {
            return true;
        }
        return false;
    }

    @Override
    public List<BasAppointmentVO> myAppointment(Long userId) {
        List<BasAppointmentVO> list = baseMapper.myAppointment(userId);
        return list;
    }

    @Override
    public Boolean cancel(Long id) {
        int count = lambdaQuery().eq(BasAppointmentEntity::getId, id).eq(BasAppointmentEntity::getSeatState, 0).count();
        if (count>0) {
            throw new RRException("已通过不可取消");
        }
        boolean a = basSeatDao.updaeSeatState(id);
        boolean b = removeById(id);
        if (a && b) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean over(Long id) {
        String time = baseMapper.getSeatDay(id);
        String s = time.split("-")[0];
        DateTime time1 = DateUtil.parse(s, "yyyy年MM月dd日");
        Date date = new Date();
        if (date.getTime() < time1.getTime()){
            throw new RRException("未到学习时间");
        }
        boolean a = basSeatDao.updaeSeatState(id);
        boolean b = removeById(id);
        if (a && b) {
            return true;
        }
        return false;
    }

}