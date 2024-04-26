package com.selfstudy.modules.bas.controller;

import com.selfstudy.common.exception.RRException;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.R;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.bas.entity.BasFloorEntity;
import com.selfstudy.modules.bas.entity.BasStudyRoomEntity;
import com.selfstudy.modules.bas.service.BasFloorService;
import com.selfstudy.modules.bas.service.BasStudyRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-02 09:31:31
 */
@RestController
@Api(tags = "楼层管理")
@RequestMapping("/basfloor")
public class BasFloorController {
    @Autowired
    private BasFloorService basFloorService;
    @Autowired
    private MessageProperties messageProperties;
    @Autowired
    private BasStudyRoomService basStudyRoomService;

    /**
     * 列表
     */
    @ApiOperation("楼层管理列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = basFloorService.queryPage(params);

        return R.ok().put("data", page);
    }

    /**
     * 保存
     */
    @ApiOperation("新增楼层")
    @PostMapping("/save")
    public R save(@RequestBody BasFloorEntity basFloor){
        boolean save = basFloorService.save(basFloor);
        if (save){
            return R.ok();
        }
        return R.error(messageProperties.getFormSaveError());
    }

    /**
     * 修改
     */
    @ApiOperation("修改楼层")
    @PostMapping("/update")
    public R update(@RequestBody BasFloorEntity basFloor){
        boolean update = basFloorService.updateById(basFloor);
        if (update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    /**
     * 删除
     */
    @ApiOperation("删除楼层")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        int count = basStudyRoomService.lambdaQuery().in(BasStudyRoomEntity::getFloorId, Arrays.asList(ids)).count();
        if (count>0){
            throw new RRException("请先删除楼层下的教室", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        boolean b = basFloorService.removeByIds(Arrays.asList(ids));
        if (b){
            return R.ok();
        }
        return R.error(messageProperties.getFormDeleteError());
    }

}
