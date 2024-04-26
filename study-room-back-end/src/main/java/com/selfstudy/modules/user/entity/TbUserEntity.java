package com.selfstudy.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 用户
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-02-02 10:59:22
 */
@Data
@TableName("tb_user")
public class TbUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * qq账号
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long qq;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 备注
	 */
	private String bz;
	/**
	 * 头像
	 */
	private String userImg;
	/**
	 * openId 微信唯一标识
	 */
	private String openId;

}
