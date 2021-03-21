package org.shield.storage.service;

import org.shield.storage.vo.FileVo;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
public interface FileService {
    /**
     * 文件上传
     *
     * @param directory
     * @param file
     * @return
     * @throws Exception
     */
    FileVo upload(String directory, MultipartFile file) throws Exception;
}