package com.selfstudy.modules.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传
 *
 * @author Mark 2891517520@qq.com
 */
@Data
@ApiModel(description = "文件上传")
public class SysFileUploadVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件地址")
    private String url;

    @ApiModelProperty("文件大小")
    private Long size;

}
