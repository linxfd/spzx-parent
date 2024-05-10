package com.atguigu.spzx.mananger.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mananger.mapper.CategoryMapper;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    //获取mapper对象，因为Listener不能让spring管理所以不能@Autowired
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<CategoryExcelVo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    // 每解析一行数据就会调用一次该方法
    @Override
    public void invoke(T o, AnalysisContext analysisContext) {
        CategoryExcelVo data = (CategoryExcelVo)o;
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // excel解析完毕以后需要执行的代码，有可能后面的数据不是100的倍数，还没有进行保存数据
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    // 保存到数据库
    private void saveData() {
        categoryMapper.batchInsert(cachedDataList);
        //遇到一个添加失败就会停止，没有完善
//        int i = 0;
//        for (CategoryExcelVo categoryExcelVo : cachedDataList) {
//            i += categoryMapper.insert(categoryExcelVo);
//        }
//        if (cachedDataList.size() != i) {
//            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
//        }
    }
}