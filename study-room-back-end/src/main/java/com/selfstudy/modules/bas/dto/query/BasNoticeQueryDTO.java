package com.selfstudy.modules.bas.dto.query;

import com.selfstudy.common.base.QueryInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公告表 查询传输模型
 *
 * @author
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
@Data
@ApiModel(value = "公告表 查询传输模型")
public class BasNoticeQueryDTO extends QueryInfoDTO {

    @ApiModelProperty(value = "发布状态")
    private Integer noticeState;

    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;
}
