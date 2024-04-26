package com.selfstudy.modules.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class TbUserVO {
    /**
     * 用户id
     */
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
}
