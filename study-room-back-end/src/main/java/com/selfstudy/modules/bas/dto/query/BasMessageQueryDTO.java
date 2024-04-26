package com.selfstudy.modules.bas.dto.query;

import com.selfstudy.common.base.QueryInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "BasMessageQueryDTO", description = "留言查询")
public class BasMessageQueryDTO extends QueryInfoDTO {
    /**
     * 0举报 1留言
     */
    @ApiModelProperty(value = "0举报 1留言")
    private Integer messageType;
}
