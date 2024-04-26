package com.selfstudy.modules.bas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.selfstudy.common.base.PageResult;
import com.selfstudy.common.base.QueryInfoDTO;
import com.selfstudy.common.utils.PageUtils;
import com.selfstudy.common.utils.Query;
import com.selfstudy.modules.bas.dao.BasNoticeDao;
import com.selfstudy.modules.bas.dto.query.BasNoticeQueryDTO;
import com.selfstudy.modules.bas.entity.BasNoticeEntity;
import com.selfstudy.modules.bas.service.BasNoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("basNoticeService")
public class BasNoticeServiceImpl extends ServiceImpl<BasNoticeDao, BasNoticeEntity> implements BasNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String noticeState = (String) params.get("noticeState");
        String noticeTitle = (String) params.get("noticeTitle");
        IPage<BasNoticeEntity> page = this.page(
                new Query<BasNoticeEntity>().getPage(params),
                new QueryWrapper<BasNoticeEntity>()
                        .like(StringUtils.isNotBlank(noticeTitle),"notice_title", noticeTitle)
                        .eq(StringUtils.isNotBlank(noticeState),"notice_state", noticeState)
        );

        return new PageUtils(page);
    }

    @Override
    public PageResult<BasNoticeEntity> queryByHelper(BasNoticeQueryDTO basNoticeQueryDTO) {
        Page<BasNoticeEntity> page = basNoticeQueryDTO.defaultIPageHelper();
        List<BasNoticeEntity> list = baseMapper.queryByHelper(basNoticeQueryDTO);
        return new PageResult<>(list, page.getTotal(), basNoticeQueryDTO);
    }

    @Override
    public PageResult<BasNoticeEntity> listNotice(QueryInfoDTO queryInfoDTO) {
        Page<BasNoticeEntity> page = queryInfoDTO.defaultIPageHelper();
        List<BasNoticeEntity> list = baseMapper.listNotice(queryInfoDTO);
        return new PageResult<>(list, page.getTotal(), queryInfoDTO);
    }
}