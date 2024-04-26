package com.selfstudy.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.modules.user.dto.query.TbUserQueryDTO;
import com.selfstudy.modules.user.entity.TbUserEntity;
import com.selfstudy.modules.user.vo.TbUserVO;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-02 10:59:22
 */
public interface TbUserService extends IService<TbUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageResult<TbUserVO> queryByHelper(TbUserQueryDTO queryDTO);

    List<TbUserEntity> listForBan();
}

