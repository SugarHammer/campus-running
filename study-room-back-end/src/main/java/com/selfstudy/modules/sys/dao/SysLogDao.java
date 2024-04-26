package com.selfstudy.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author Mark 2891517520@qq.com
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
