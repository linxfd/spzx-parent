package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 */

public interface SysUserService {
    /**
     * 根据用户名查询用户数据
     * @return
     */
    LoginVo login(LoginDto loginDto) ;

//    //获取用户信息
//    SysUser getUserInfo(String token);

    //用户退出,删除redis中的token
    void logout(String token);

    //分页查询
    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    //新增用户
    void saveSysUser(SysUser sysUser);

    //修改用户
    void updateSysUser(SysUser sysUser);

    //删除用户
    void deleteById(Long userId);

    //保存角色数据
    void doAssign(AssginRoleDto assginRoleDto);
}
