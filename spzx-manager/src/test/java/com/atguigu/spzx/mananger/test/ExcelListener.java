package com.atguigu.spzx.mananger.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {

    //可以通过实例获取该值
    private List<T> datas = new ArrayList<>();

    public List<T> getDatas() {
        return datas;
    }
    /**
     * 逐行读取数据
     * 从第二行开始读取，把每行读取到的内容封装到o对象里面
     * @param o
     * @param analysisContext
     */
    @Override
    public void invoke(T o, AnalysisContext analysisContext) {  // 每解析一行数据就会调用一次该方法
        datas.add(o);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
    }

    /**
     * 所有数据解析完成了，会来调用该方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // excel解析完毕以后需要执行的代码
    }
}
