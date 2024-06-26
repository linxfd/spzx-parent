package com.atguigu.spzx.mananger.controller;

import com.atguigu.spzx.mananger.service.FileUploadService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *文件上传到Minio的接口
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService ;

    //<input type="file" name="file">  就是name="file"要一致
    @Operation(summary = "文件上传")
    @PostMapping(value = "/fileUpload")
    public Result<String> fileUploadService(@RequestParam(value = "file") MultipartFile file) {
        String fileUrl = fileUploadService.fileUpload(file) ;
        return Result.build(fileUrl , ResultCodeEnum.SUCCESS) ;
    }
}
