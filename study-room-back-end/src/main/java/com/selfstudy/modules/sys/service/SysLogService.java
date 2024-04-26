package com.selfstudy.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.selfstudy.modules.sys.entity.SysLogEntity;
import com.selfstudy.common.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark 2891517520@qq.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
