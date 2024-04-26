package com.selfstudy.modules.bas.dto.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 自习室表
 *
 * @author
 * @email 2891517520@qq.com
 * @date 2023-02-01 16:46:47
 */
@Data
@ApiModel(value = "自习室表新增")
public class BasStudyRoomSaveDTO {
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
