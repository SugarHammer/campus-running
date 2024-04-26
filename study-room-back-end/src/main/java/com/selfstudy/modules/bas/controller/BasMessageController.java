package com.selfstudy.modules.bas.controller;

import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.R;
import com.selfstudy.modules.bas.dto.query.BasMessageQueryDTO;
import com.selfstudy.modules.bas.entity.BasMessageEntity;
import com.selfstudy.modules.bas.service.BasMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;



/**
 * 
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-03 10:56:39
 */
@RestController
@Api(tags = "留言管理")
@RequestMapping("/basmessage")
public class BasMessageController {
    @Autowired
    private BasMessageService basMessageService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("留言管理列表")
    public R list(BasMessageQueryDTO queryDTO){
        PageResult<BasMessageEntity> pageResult = basMessageService.queryByHelper(queryDTO);
        return R.ok().put("data", pageResult);
    }



    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("留言删除")
    public R delete(@RequestBody Long[] ids){
		basMessageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
