package com.selfstudy.modules.bas.dto.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公告表 修改传输模型
 *
 * @author
 * @email 2891517520@qq.com
 * @date 2023-01-31 14:21:26
 */
@Data
@ApiModel(value = "BasNoticeSaveDTO", description = "公告表修改")
public class BasNoticeUpdateDTO {

    /**
     * 公告ID
     */
    @ApiModelProperty(value = "公告ID")
    private Long noticeId;
    /**
     * '公告标题'
     */
    @ApiModelProperty(value = "公告标题")
    private String noticeTitle;
    /**
     * '排序'
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容")
    private String noticeContent;
}
