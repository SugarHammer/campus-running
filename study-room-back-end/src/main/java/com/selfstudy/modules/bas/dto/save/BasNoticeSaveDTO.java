package com.selfstudy.modules.bas.dto.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公告表 新增传输模型
 *
 * @author
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
@Data
@ApiModel(value = "BasNoticeSaveDTO", description = "公告表新增")
public class BasNoticeSaveDTO {
    /**
     * '排序'
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * '公告标题'
     */
    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;
    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容")
    private String noticeContent;
}
