package com.selfstudy.modules.bas.dto.query;

import com.selfstudy.common.base.QueryInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BasAppointmentQueryDTO", description = "预约信息查询条件")
public class BasAppointmentQueryDTO extends QueryInfoDTO {
    /**
     * 预约者电话
     */
    @ApiModelProperty(value = "预约者电话")
    private String seatPhone;
    /**
     * 预约者姓名
     */
    @ApiModelProperty(value = "预约者姓名")
    private String seatName;
    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态")
    private Integer seatState;
}
