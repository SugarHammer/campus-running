package com.selfstudy.modules.bas.dto.query;

import com.selfstudy.common.base.QueryInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BasSeatQueryDTO", description = "自习室查询")
public class BasSeatQueryDTO extends QueryInfoDTO {

    /**
     * 座位预约状态【0开放，1已预约】
     */
    @ApiModelProperty(value = "座位预约状态【0开放，1已预约】")
    private Integer roomState;

    /**
     * 座位名称
     */
    @ApiModelProperty(value = "座位名称")
    private String seatName;
    /**
     * 自习室名称
     */
    @ApiModelProperty(value = "自习室名称")
    private String roomName;
}
