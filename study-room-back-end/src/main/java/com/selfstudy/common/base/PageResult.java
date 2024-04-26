package com.selfstudy.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @param <T>
 * @author 2891517520@qq.com
 */

@Data
public class PageResult<T> {

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long totalCount;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Long pageSize;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Long totalPage;
    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数")
    private Long currPage;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    List<T> content;

    public PageResult(List<T> content, Long totalCount, QueryInfoDTO queryInfoDTO) {
        this.totalCount = totalCount;
        this.content = content;
        this.pageSize = Long.valueOf(queryInfoDTO.getLimit());
        this.currPage = Long.valueOf(queryInfoDTO.getPage());
        this.totalPage = new Double(Math.ceil(totalCount.doubleValue() / pageSize)).longValue();
    }


}
