package com.selfstudy.common.base;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 2891517520@qq.com
 */
@Data
@ApiModel(value = "查询信息数据传输模型")
public class QueryInfoDTO {


    /**
     * 默认当前页数
     */
    public static final Integer DEFAULT_CURRENT_PAGE = 1;

    /**
     * 默认页数
     */
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数, 默认1", required = true)
    private Integer page;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数, 默认10", required = true)
    private Integer limit;


    /**
     * 默认分页，在使用时PageFactory.defaultPage会自动获取limit和pageNo参数
     */
    public <T> IPage<T> defaultIPage() {
        //第几页
        if (ObjectUtil.isEmpty(page) || page.equals(0)) {
            page = DEFAULT_CURRENT_PAGE;
        }
        //每页条数
        if (ObjectUtil.isEmpty(limit) || limit.equals(0)) {
            limit = DEFAULT_PAGE_SIZE;
        }
        return new Page<>(page, limit);
    }

    /**
     * 默认分页，在使用时PageFactory.defaultPage会自动获取limit和pageNo参数
     */
    public <T> com.github.pagehelper.Page<T> defaultIPageHelper() {
        //第几页
        if (ObjectUtil.isEmpty(page) || page.equals(0)) {
            page = DEFAULT_CURRENT_PAGE;
        }
        //每页条数
        if (ObjectUtil.isEmpty(limit) || limit.equals(0)) {
            limit = DEFAULT_PAGE_SIZE;
        }
        return PageHelper.startPage(page, limit);
    }

}
