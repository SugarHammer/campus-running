package com.selfstudy.modules.bas.dto.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BasSeatSaveDTO", description = "自习室新增")
public class BasSeatSaveDTO {
    /**
     * 自习室id
     */
    @ApiModelProperty(value = "自习室id")
    private Long roomId;
    /**
     * 座位名称
     */
    @ApiModelProperty(value = "座位名称")
    private String seatName;
    /**
     * 座位预约状态【0开放1已预约】
     */
    @ApiModelProperty(value = "座位预约状态【0开放，1已预约】")
    private Integer roomState;
}
