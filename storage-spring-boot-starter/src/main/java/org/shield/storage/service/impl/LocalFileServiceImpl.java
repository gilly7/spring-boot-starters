
package org.shield.storage.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.shield.storage.service.FileService;
import org.shield.storage.vo.FileVo;
import com.pig4cloud.plugin.oss.OssProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * 兼容 AWS 接口的 OSS 文件管理服务
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public class LocalFileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    /**
     * 本地文件存储
     *
     * <ul>
     * <li>customDomain 做为对外访问的URL入口, 与 endpoint 对应: customDomain/{bucketName}/{director}/{file}</li>
     * <li>endpoint 做为文件夹实际目录： 如 /var/www/html/public</li>
     * <li>bucketName 做为文件夹, 如 files, 文件将存储于 {endpoint}/{bucketName}/{directory}/{file}</li>
     * </ul>
     */
    @Override
    public FileVo upload(String directory, MultipartFile file) throws Exception {
        String originName = file.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf(".") + 1);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + ext;
        String pathFileName = ossProperties.getBucketName() + "/" + directory + "/" + fileName;

        Path filepath = Paths.get(ossProperties.getRegion() + "/" + pathFileName);
        Files.createDirectories(
                Paths.get(ossProperties.getRegion() + "/" + ossProperties.getBucketName() + "/" + directory));
        file.transferTo(filepath);

        FileVo fileVo = new FileVo();
        fileVo.setName(originName);
        fileVo.setUrl(String.join("", ossProperties.getEndpoint(), "/", directory + "/" + fileName));
        fileVo.setKey(fileName);
        fileVo.setHash(null);
        return fileVo;
    }
}
