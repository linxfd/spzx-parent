package com.atguigu.spzx.mananger.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 */
public interface FileUploadService {

    String fileUpload(MultipartFile file);
}
