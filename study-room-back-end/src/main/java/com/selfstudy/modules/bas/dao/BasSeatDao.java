package com.selfstudy.modules.bas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selfstudy.modules.bas.dto.query.BasSeatQueryDTO;
import com.selfstudy.modules.bas.entity.BasSeatEntity;
import com.selfstudy.modules.bas.vo.BasSeatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 座位表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@Mapper
public interface BasSeatDao extends BaseMapper<BasSeatEntity> {

    List<BasSeatVO> queryByHelper(@Param("queryDTO") BasSeatQueryDTO queryDTO);
    
    @Update("update bas_seat set room_state = 0 where (select seat_id from bas_appointment where id = #{id})")
    Boolean updaeSeatState(Long id);

    List<Long> getSeatID(@Param("list") List<Long> asList);
}
