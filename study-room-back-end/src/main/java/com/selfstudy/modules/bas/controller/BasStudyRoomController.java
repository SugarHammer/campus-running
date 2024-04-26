package com.selfstudy.modules.bas.controller;

import cn.hutool.core.bean.BeanUtil;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.exception.RRException;
import com.selfstudy.common.utils.R;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.bas.dto.query.BasStudyRoomQueryDTO;
import com.selfstudy.modules.bas.dto.save.BasStudyRoomSaveDTO;
import com.selfstudy.modules.bas.dto.update.BasStudyRoomUpdateDTO;
import com.selfstudy.modules.bas.entity.BasSeatEntity;
import com.selfstudy.modules.bas.entity.BasStudyRoomEntity;
import com.selfstudy.modules.bas.service.BasSeatService;
import com.selfstudy.modules.bas.service.BasStudyRoomService;
import com.selfstudy.modules.bas.vo.BasStudyRoomVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 自习室表
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@RestController
@Api(tags = "自习室管理")
@RequestMapping("/basstudyroom")
public class BasStudyRoomController {
    @Autowired
    private BasStudyRoomService basStudyRoomService;
    @Autowired
    private MessageProperties messageProperties;
    @Autowired
    private BasSeatService basSeatService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("自习室分页列表")
    public R list(BasStudyRoomQueryDTO queryDTO){
        PageResult<BasStudyRoomVO> pageResult = basStudyRoomService.queryByHelper(queryDTO);
        return R.ok().put("data", pageResult);
    }

    @GetMapping("/AllList")
    @ApiOperation("获取所有自习室")
    public R all(){
        List<BasStudyRoomEntity> list = basStudyRoomService.list();
        return R.ok().put("data", list);
    }
    /**
     * 信息
     */
    @ApiOperation("自习室详情")
    @GetMapping("/info/{roomId}")
    public R info(@PathVariable("roomId") Long roomId){
		BasStudyRoomEntity basStudyRoom = basStudyRoomService.getById(roomId);

        return R.ok().put("data", basStudyRoom);
    }

    /**
     * 保存
     */
    @ApiOperation("自习室保存")
    @PostMapping("/save")
    public R save(@RequestBody BasStudyRoomSaveDTO saveDTO){
        BasStudyRoomEntity basStudyRoomEntity = BeanUtil.copyProperties(saveDTO, BasStudyRoomEntity.class);
        boolean save = basStudyRoomService.save(basStudyRoomEntity);
        if (save){
            return R.ok();
        }
        return R.error(messageProperties.getFormSaveError());
    }

    /**
     * 修改
     */
    @ApiOperation("自习室修改")
    @PostMapping("/update")
    public R update(@RequestBody BasStudyRoomUpdateDTO updateDTO){
        BasStudyRoomEntity basStudyRoomEntity = BeanUtil.copyProperties(updateDTO, BasStudyRoomEntity.class);
        boolean update = basStudyRoomService.updateById(basStudyRoomEntity);
        if (update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    /**
     * 删除
     */
    @ApiOperation("自习室删除")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] roomIds){
        int count = basSeatService.lambdaQuery().in(BasSeatEntity::getRoomId, Arrays.asList(roomIds)).count();
        if (count>0){
            throw new RRException("请先教室下的删除座位", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        boolean b = basStudyRoomService.removeByIds(Arrays.asList(roomIds));
        if (b){
            return R.ok();
        }

        return R.error(messageProperties.getFormDeleteError());
    }

}
