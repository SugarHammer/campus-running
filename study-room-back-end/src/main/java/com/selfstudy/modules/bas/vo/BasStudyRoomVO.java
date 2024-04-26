package com.selfstudy.modules.bas.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BasStudyRoomVO {

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 楼层
     */
    @ApiModelProperty(value = "楼层（一楼，二楼）")
    private String floor;

}
