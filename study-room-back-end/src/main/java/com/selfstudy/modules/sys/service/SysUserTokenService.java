package com.selfstudy.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.selfstudy.modules.sys.entity.SysUserTokenEntity;
import com.selfstudy.common.utils.R;

/**
 * 用户Token
 *
 * @author Mark 2891517520@qq.com
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
