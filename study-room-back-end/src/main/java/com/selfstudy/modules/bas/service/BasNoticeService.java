package com.selfstudy.modules.bas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.base.QueryInfoDTO;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.modules.bas.dto.query.BasNoticeQueryDTO;
import com.selfstudy.modules.bas.entity.BasNoticeEntity;

import java.util.Map;

/**
 * 公告表
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
public interface BasNoticeService extends IService<BasNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageResult<BasNoticeEntity> queryByHelper(BasNoticeQueryDTO basNoticeQueryDTO);

    /**
     * 小程序公告
     * @param basNoticeQueryDTO
     * @return
     */
    PageResult<BasNoticeEntity> listNotice(QueryInfoDTO basNoticeQueryDTO);
}

