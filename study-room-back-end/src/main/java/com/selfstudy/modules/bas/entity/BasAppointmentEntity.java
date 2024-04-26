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
 * 预约信息表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@Data
@TableName("bas_appointment")
public class BasAppointmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 座位id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long seatId;
	/**
	 * 预约者id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userId;
	/**
	 * 预约者电话
	 */
	private String seatPhone;
	/**
	 * 预约者姓名
	 */
	private String seatName;
	/**
	 * 预约者专业班级
	 */
	private String seatClass;
	/**
	 * 预约时间
	 */
	private Date seatTime;
	/**
	 * 审核状态
	 */
	private Integer seatState;
	/**
	 * 预约时间区间
	 */
	private String seatDay;
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
