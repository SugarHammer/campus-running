package com.selfstudy.modules.bas.controller;

import cn.hutool.core.bean.BeanUtil;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.R;
import com.selfstudy.config.MessageProperties;
import com.selfstudy.modules.bas.dto.save.BasNoticeSaveDTO;
import com.selfstudy.modules.bas.dto.update.BasNoticeUpdateDTO;
import com.selfstudy.modules.bas.entity.BasNoticeEntity;
import com.selfstudy.modules.bas.service.BasNoticeService;
import com.selfstudy.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 公告表
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
@RestController
@RequestMapping("/notice")
@Api(tags = "后台公告")
public class BasNoticeController extends AbstractController {
    @Autowired
    private BasNoticeService basNoticeService;
    @Autowired
    private MessageProperties messageProperties;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表(noticeState,noticeTitle)")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = basNoticeService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 详情
     */
    @GetMapping("/info/{noticeId}")
    @ApiOperation("详情")
    public R info(@ApiParam(value = "noticeId") @PathVariable("noticeId") Long noticeId){
		BasNoticeEntity basNotice = basNoticeService.getById(noticeId);

        return R.ok().put("data", basNotice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody BasNoticeSaveDTO basNotice){
        BasNoticeEntity basNoticeEntity = BeanUtil.copyProperties(basNotice, BasNoticeEntity.class);
        basNoticeEntity.setCreateUserId(getUserId());
        boolean save = basNoticeService.save(basNoticeEntity);
        if(save){
            return R.ok();
        }
        return R.error(messageProperties.getFormSaveError());
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody BasNoticeUpdateDTO basNotice){
        BasNoticeEntity basNoticeEntity = BeanUtil.copyProperties(basNotice, BasNoticeEntity.class);
        boolean update = basNoticeService.updateById(basNoticeEntity);
        if (update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    /**
     * 发布状态
     * @param id
     * @return
     */
    @PostMapping("/updateStateOn")
    @ApiOperation("发布状态")
    public R updateStateOn(@ApiParam(value = "id") Long id){
        boolean update = basNoticeService.lambdaUpdate().eq(BasNoticeEntity::getNoticeId, id).set(BasNoticeEntity::getNoticeState, 0).update();
        if (update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }

    /**
     * 取消发布状态
     * @param id
     * @return
     */
    @PostMapping("/updateStateOff")
    @ApiOperation("取消发布状态")
    public R updateStateOff(@ApiParam(value = "id") Long id){
        boolean update = basNoticeService.lambdaUpdate().eq(BasNoticeEntity::getNoticeId, id).set(BasNoticeEntity::getNoticeState, 1).update();
        if (update){
            return R.ok();
        }
        return R.error(messageProperties.getFormUpdateError());
    }


    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    public R delete(@RequestBody Long[] noticeIds){
        boolean b = basNoticeService.removeByIds(Arrays.asList(noticeIds));
        if (b){
            return R.ok();
        }
        return R.error(messageProperties.getFormDeleteError());
    }

}
