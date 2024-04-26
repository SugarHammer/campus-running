package com.selfstudy.modules.bas.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 自习室表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@Data
@TableName("bas_study_room")
public class BasStudyRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自习室id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long roomId;
	/**
	 * 楼层id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long floorId;
	/**
	 * 自习室名称
	 */
	private String roomName;
	/**
	 * 开放时间
	 */
	private String openingTime;
	/**
	 * 结束时间
	 */
	private String closeTime;
	/**
	 * 创建者ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 删除标识0[正常;1删除]
	 */
	@TableLogic
	private Integer deleteFlag;
	/**
	 * 删除人ID
	 */
	private String deleteUserId;
	/**
	 * 删除时间
	 */
	private Date deleteTime;


}
