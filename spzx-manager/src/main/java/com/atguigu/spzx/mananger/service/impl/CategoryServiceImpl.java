package com.atguigu.spzx.mananger.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mananger.listener.ExcelListener;
import com.atguigu.spzx.mananger.mapper.CategoryMapper;
import com.atguigu.spzx.mananger.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper ;

    @Override
    public List<Category> findByParentId(Long parentId) {
        // 根据分类id查询它下面的所有的子分类数据
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);
        if(!CollectionUtils.isEmpty(categoryList)) {
            // 遍历分类的集合，获取每一个分类数据
            categoryList.forEach(item -> {
                // 查询该分类下子分类的数量
                int count = categoryMapper.countByParentId(item.getId());
                if(count > 0) {
                    item.setHasChildren(true);
                } else {
                    item.setHasChildren(false);
                }
            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 设置响应结果类型
            response.setContentType("application/vnd.ms-excel");  // 设置响应结果类型为excel表格
            response.setCharacterEncoding("utf-8");  // 设置响应结果编码为utf-8,防止有中文乱码

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            // 设置响应头信息：Content-disposition表示要下载文件，filename表示下载时的文件名
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            // 查询数据库中的数据
            List<Category> categoryList = categoryMapper.selectAll();
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());

            // 将从数据库中查询到的Category对象转换成CategoryExcelVo对象
            for(Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
                categoryExcelVoList.add(categoryExcelVo);
            }
            // 写出数据到浏览器端
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据") // 设置sheet名称
                    .doWrite(categoryExcelVoList);  // 开始写出数据到浏览器端
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            //创建监听器对象，传递mapper对象
            ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>(categoryMapper);
            //调用read方法读取excel数据
            EasyExcel.read(file.getInputStream(),
                    CategoryExcelVo.class,
                    excelListener).sheet().doRead();
        } catch (IOException e) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }


}
