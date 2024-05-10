package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @version 1.0
 */
@Mapper
public interface SysOperLogMapper {

    public abstract void insert(SysOperLog sysOperLog);
}
