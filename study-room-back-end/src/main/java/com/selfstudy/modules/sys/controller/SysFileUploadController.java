package com.selfstudy.modules.sys.controller;

import com.selfstudy.common.utils.R;
import com.selfstudy.modules.sys.service.StorageService;
import com.selfstudy.modules.sys.vo.SysFileUploadVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author Mark 2891517520@qq.com
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件上传接口")
public class SysFileUploadController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    @ApiOperation("本地上传")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error("请选择需要上传的文件");
        }

        // 上传路径
        String path = storageService.getPath(file.getOriginalFilename());
        // 上传文件
        String url = storageService.upload(file.getBytes(), path);

        SysFileUploadVO vo = new SysFileUploadVO();
        vo.setUrl(url);
        vo.setSize(file.getSize());
        vo.setName(file.getOriginalFilename());

        return R.ok().put("data",vo);
    }

}
