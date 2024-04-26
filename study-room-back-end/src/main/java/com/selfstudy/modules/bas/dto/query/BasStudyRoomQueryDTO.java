package com.selfstudy.modules.bas.dto.query;

import com.selfstudy.common.base.QueryInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自习室表 查询传输模型
 *
 * @author
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
@Data
@ApiModel(value = "自习室表 查询传输模型")
public class BasStudyRoomQueryDTO extends QueryInfoDTO {

    /**
     * 自习室名称
     */
    @ApiModelProperty(value = "自习室名称")
    private String roomName;


    /**
     * 楼层id
     */
    @ApiModelProperty(value = "楼层id")
    private Long floorId;
}
