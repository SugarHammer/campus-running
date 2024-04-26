package com.selfstudy.modules.bas.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 座位表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@Data
@TableName("bas_seat")
public class BasSeatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 座位id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long seatId;
	/**
	 * 自习室id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long roomId;
	/**
	 * 座位名称
	 */
	private String seatName;
	/**
	 * 座位预约状态【0开放，1已预约】
	 */
	private Integer roomState;
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
