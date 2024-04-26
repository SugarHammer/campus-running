package com.selfstudy.modules.bas.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-03 10:56:39
 */
@Data
@TableName("bas_message")
public class BasMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 留言内容
	 */
	private String message;
	/**
	 * 0留言 1举报
	 */
	private Integer messageType;
	/**
	 * 留言用户
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userId;
	/**
	 * 留言时间
	 */
	private Date messageTime;
	/**
	 * 用户名
	 */
	private String username;

}
