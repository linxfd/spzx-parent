package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    SysUser selectByUserName(String userName) ;

    //分页查询
    List<SysUser> findByPage(SysUserDto sysUserDto);

    //添加用户
    public abstract void saveSysUser(SysUser sysUser);

    //修改用户
    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
