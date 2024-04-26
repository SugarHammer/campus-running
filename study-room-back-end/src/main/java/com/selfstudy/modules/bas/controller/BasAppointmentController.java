package com.selfstudy.modules.bas.controller;

import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.R;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.bas.dto.query.BasAppointmentQueryDTO;
import com.selfstudy.modules.bas.entity.BasAppointmentEntity;
import com.selfstudy.modules.bas.entity.BasSeatEntity;
import com.selfstudy.modules.bas.service.BasAppointmentService;
import com.selfstudy.modules.bas.service.BasSeatService;
import com.selfstudy.modules.bas.vo.CountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 预约信息表
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@RestController
@Api(tags = "预约信息管理")
@RequestMapping("/basappointment")
public class BasAppointmentController {
    @Autowired
    private BasAppointmentService basAppointmentService;
    @Autowired
    private MessageProperties messageProperties;
    @Autowired
    private BasSeatService basSeatService;

    /**
     * 统计
     */
    @GetMapping("/count")
    @ApiOperation("每日预约信息统计")
    public R count(@ApiParam(value = "查询时间(默认传当天)") @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        List<CountVO> countVO = basAppointmentService.countAll(date);
        return R.ok().put("data",countVO);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("预约信息分页列表")
    public R list(BasAppointmentQueryDTO queryDTO){
        PageResult<BasAppointmentEntity> pageResult = basAppointmentService.queryByHelper(queryDTO);
        return R.ok().put("data", pageResult);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("每日预约信息详情")
    public R info(@ApiParam(value = "预约id") @PathVariable("id") Long id){
		BasAppointmentEntity basAppointment = basAppointmentService.getById(id);
        return R.ok().put("basAppointment", basAppointment);
    }


    /**
     * 审核通过
     */
    @PostMapping("/stateOn")
    @ApiOperation("审核通过0，1未审核")
    @Transactional(rollbackFor = Exception.class)
    public R stateOn(@ApiParam(value = "预约id") Long id,Long seatId){
        boolean update = basSeatService.lambdaUpdate().eq(BasSeatEntity::getSeatId, seatId).set(BasSeatEntity::getRoomState, 1).update();
        boolean update1 = basAppointmentService.lambdaUpdate().eq(BasAppointmentEntity::getId, id).set(BasAppointmentEntity::getSeatState, 0).update();
        if (update1 && update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }
    /**
     * 审核不通过
     */
    @PostMapping("/stateOff")
    @ApiOperation("审核不通过2，1未审核")
    @Transactional(rollbackFor = Exception.class)
    public R stateOff(@ApiParam(value = "预约id") Long id,Long seatId){
        boolean update = basSeatService.lambdaUpdate().eq(BasSeatEntity::getSeatId, seatId).set(BasSeatEntity::getRoomState, 0).update();

        boolean update1 = basAppointmentService.lambdaUpdate().eq(BasAppointmentEntity::getId, id).set(BasAppointmentEntity::getSeatState, 2).update();
        if (update1 && update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除预约")
    @Transactional(rollbackFor = Exception.class)
    public R delete(@RequestBody Long[] ids){
        List<Long> seatId = basSeatService.getSeatID(Arrays.asList(ids));
        boolean update = basSeatService.lambdaUpdate().in(BasSeatEntity::getSeatId, seatId).set(BasSeatEntity::getRoomState, 0).update();
        boolean b = basAppointmentService.removeByIds(Arrays.asList(ids));
        if (b && update){
            return R.ok();
        }
        return R.error(messageProperties.getFormDeleteError());
    }




}
