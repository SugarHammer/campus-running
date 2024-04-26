package com.selfstudy.modules.bas.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class BasSeatVO {
    /**
     * 座位id
     */
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
     * 自习室名称
     */
    private String roomName;
}
