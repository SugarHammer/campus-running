package com.selfstudy.modules.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.modules.user.dto.query.TbUserQueryDTO;
import com.selfstudy.modules.user.entity.TbUserEntity;
import com.selfstudy.modules.user.vo.TbUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-02 10:59:22
 */
@Mapper
public interface TbUserDao extends BaseMapper<TbUserEntity> {

    List<TbUserVO> queryByHelper(@Param("queryDTO") TbUserQueryDTO queryDTO);

    @Select("select * from tb_user where `status` = 0")
    List<TbUserEntity> listForBan();
}
