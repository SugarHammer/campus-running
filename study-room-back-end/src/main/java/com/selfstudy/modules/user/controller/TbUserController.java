package com.selfstudy.modules.user.controller;

import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.R;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.applet.annotation.Login;
import com.selfstudy.modules.user.dto.query.TbUserQueryDTO;
import com.selfstudy.modules.user.entity.TbUserEntity;
import com.selfstudy.modules.user.service.TbUserService;
import com.selfstudy.modules.user.vo.TbUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



/**
 * 用户
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-02 10:59:22
 */
@RestController
@Api(tags = "小程序用户")
@RequestMapping("/tbuser")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private MessageProperties messageProperties;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("用户分页查询")
    public R list(TbUserQueryDTO queryDTO){
        PageResult<TbUserVO> pageResult = tbUserService.queryByHelper(queryDTO);
        return R.ok().put("data", pageResult);
    }


    /**
     * 信息
     */
    @ApiOperation("用户详情")
    @GetMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
		TbUserEntity tbUser = tbUserService.getById(userId);

        return R.ok().put("data", tbUser);
    }



    /**
     * 禁用账号
     */
    @ApiOperation("禁用账号")
    @PostMapping("/ban")
    public R ban(Long id){
        boolean update = tbUserService.lambdaUpdate().eq(TbUserEntity::getUserId, id).set(TbUserEntity::getStatus, 0).update();
        if (update){
            return R.ok();
        }

        return R.error(messageProperties.getFormUpdateError());
    }
    /**
     * 解除封禁
     */
    @ApiOperation("解除封禁")
    @PostMapping("/relieveBan")
    public R relieveBan(Long id){
        boolean update = tbUserService.lambdaUpdate().eq(TbUserEntity::getUserId, id).set(TbUserEntity::getStatus, 1).update();
        if (update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    /**
     * 删除
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] userIds){
        boolean b = tbUserService.removeByIds(Arrays.asList(userIds));
        if (b){
            return R.ok();
        }
        return R.error(messageProperties.getFormDeleteError());
    }

}
