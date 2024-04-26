package com.selfstudy.modules.bas.dto.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "自习室表修改")
public class BasStudyRoomUpdateDTO {
    /**
     * 自习室id
     */
    @ApiModelProperty(value = "自习室id")
    private Long roomId;
    /**
     * 楼层id
     */
    @ApiModelProperty(value = "楼层id")
    private Long floorId;
    /**
     * 自习室名称
     */
    @ApiModelProperty(value = "自习室名称")
    private String roomName;
    /**
     * 开放时间
     */
    @ApiModelProperty(value = "开放时间")
    private String openingTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String closeTime;
}
