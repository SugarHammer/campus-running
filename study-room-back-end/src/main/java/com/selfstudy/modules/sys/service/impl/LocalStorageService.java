package com.selfstudy.modules.sys.service.impl;

import com.selfstudy.modules.sys.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;

/**
 * 本地存储
 *
 * @author Mark 2891517520@qq.com
 */
@Service
public class LocalStorageService extends StorageService {


    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }


    @Override
    public String upload(InputStream inputStream, String path) {

        String substring;
        try {
            String basePath = ResourceUtils.getURL("classpath:").getPath();
            substring = basePath.substring(1, 3) + "/upload";
            File file = new File(substring + File.separator + path);
            // 没有目录，则自动创建目录
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("目录 '" + parent + "' 创建失败");
            }
            FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
        } catch (Exception e) {
            throw new RuntimeException("上传文件失败：", e);
        }

        return substring + "/" + path;
    }
}
