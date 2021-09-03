package org.shield.storage.controller;

import org.shield.rest.exception.BadRequestException;
import org.shield.storage.service.FileService;
import org.shield.storage.vo.FileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zacksleo@gmail.com
 */
@Api(tags = "文件")
@RestController
@RequestMapping("files/{directory}")
public class FileController {

    @Autowired
    private FileService service;

    @Value("${oss.max-file-size:2MB}")
    private String maxFileSize;

    /**
     * 上传文件 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
     *
     * @param file 资源
     * @return R(bucketName, filename)
     */
    @ApiOperation("文件上传")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiImplicitParams({@ApiImplicitParam(name = "directory", value = "文件夹名称", dataType = "String", paramType = "path",
            required = true)})
    @ApiResponses({@ApiResponse(code = 413, message = "文件大小超过限制")})
    public FileVo upload(@PathVariable("directory") String directory,
            @RequestPart(required = true) @RequestParam("file") MultipartFile file, HttpServletRequest request)
            throws Exception {
        if (DataSize.parse(this.maxFileSize).toBytes() < file.getSize()) {
            throw new BadRequestException(String.format("文件大小超过限制: %s", this.maxFileSize));
        }
        return service.upload(directory, file);
    }
}
