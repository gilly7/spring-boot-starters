
package org.shield.storage.service.impl;

import java.io.InputStream;
import java.util.UUID;

import com.amazonaws.services.s3.model.PutObjectResult;
import org.shield.storage.service.FileService;
import org.shield.storage.vo.FileVo;
import com.pig4cloud.plugin.oss.OssProperties;
import com.pig4cloud.plugin.oss.service.OssTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * 兼容 AWS 接口的 OSS 文件管理服务
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public class OssFileServiceImpl implements FileService {

    public OssFileServiceImpl() {

    }

    public OssFileServiceImpl(OssTemplate template, OssProperties ossProperties) {
        this.template = template;
        this.ossProperties = ossProperties;
    }

    @Autowired
    private OssTemplate template;

    @Autowired
    private OssProperties ossProperties;

    @Override
    public FileVo upload(String directory, MultipartFile file) throws Exception {
        String originName = file.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + ext;
        InputStream stream = file.getInputStream();
        String pathFileName = directory + "/" + fileName;
        PutObjectResult result = template.putObject(ossProperties.getBucketName(), pathFileName, stream,
                (long) stream.available(), "application/octet-stream");
        FileVo fileVo = new FileVo();
        fileVo.setName(originName);
        fileVo.setUrl(String.join("", "https://", ossProperties.getBucketName(), ".", ossProperties.getEndpoint(), "/",
                pathFileName));
        fileVo.setKey(fileName);
        fileVo.setHash(result.getETag());
        return fileVo;
    }
}
