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
 * 公告表
 * 
 * @author 
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
@Data
@TableName("bas_notice")
public class BasNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 公告ID
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long noticeId;
	/**
	 * '发布状态'
	 */
	private Integer noticeState;
	/**
	 * '排序'
	 */
	private Integer sort;
	/**
	 * '公告标题'
	 */
	private String noticeTitle;
	/**
	 * 公告内容
	 */
	private String noticeContent;
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
	 * 删除标识[0正常;1删除]
	 */
	@TableLogic
	private Integer deleteFlag;

}
