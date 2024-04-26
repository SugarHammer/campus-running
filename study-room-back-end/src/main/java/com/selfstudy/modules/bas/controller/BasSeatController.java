package com.selfstudy.modules.bas.controller;

import cn.hutool.core.bean.BeanUtil;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.R;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.bas.dto.query.BasSeatQueryDTO;
import com.selfstudy.modules.bas.dto.save.BasSeatSaveDTO;
import com.selfstudy.modules.bas.dto.update.BasSeatUpdateDTO;
import com.selfstudy.modules.bas.entity.BasSeatEntity;
import com.selfstudy.modules.bas.service.BasSeatService;
import com.selfstudy.modules.bas.vo.BasSeatVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;



/**
 * 座位表
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@RestController
@Api(tags = "座位管理")
@RequestMapping("/basseat")
public class BasSeatController {
    @Autowired
    private BasSeatService basSeatService;
    @Autowired
    private MessageProperties messageProperties;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("座位分页列表")
    public R list(BasSeatQueryDTO queryDTO){
        PageResult<BasSeatVO> pageResult = basSeatService.queryByHelper(queryDTO);
        return R.ok().put("data", pageResult);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{roomId}")
    @ApiOperation("座位分页详情")
    public R info(@PathVariable("roomId") Long roomId){
		BasSeatEntity basSeat = basSeatService.getById(roomId);

        return R.ok().put("data", basSeat);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("座位分页保存")
    public R save(@RequestBody BasSeatSaveDTO saveDTO){
        BasSeatEntity basSeatEntity = BeanUtil.copyProperties(saveDTO, BasSeatEntity.class);
        boolean save = basSeatService.save(basSeatEntity);
        if (save){
            return R.ok();
        }

        return R.error(messageProperties.getFormSaveError());
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("座位分页修改")
    public R update(@RequestBody BasSeatUpdateDTO updateDTO){
        BasSeatEntity basSeatEntity = BeanUtil.copyProperties(updateDTO, BasSeatEntity.class);
        basSeatService.updateById(basSeatEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("座位删除")
    public R delete(@RequestBody Long[] roomIds){
		basSeatService.removeByIds(Arrays.asList(roomIds));

        return R.ok();
    }

}
