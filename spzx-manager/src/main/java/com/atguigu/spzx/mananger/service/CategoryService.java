package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0
 */
public interface CategoryService {
    // 根据父id查询分类
    List<Category> findByParentId(Long parentId);

    // 导出分类表格
    void exportData(HttpServletResponse response);

    // 导入分类表格
    void importData(MultipartFile file);
}
