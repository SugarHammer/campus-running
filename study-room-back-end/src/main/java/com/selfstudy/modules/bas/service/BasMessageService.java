package com.selfstudy.modules.bas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.modules.bas.dto.query.BasMessageQueryDTO;
import com.selfstudy.modules.bas.entity.BasMessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-03 10:56:39
 */
public interface BasMessageService extends IService<BasMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageResult<BasMessageEntity> queryByHelper(BasMessageQueryDTO queryDTO);
}

