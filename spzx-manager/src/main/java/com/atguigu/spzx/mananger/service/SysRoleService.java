package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @version 1.0
 */
public interface SysRoleService {

    //分页查询
    PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto);

    //添加角色
    void saveSysRole(SysRole sysRole);

    //修改角色

    void updateSysRole(SysRole sysRole);

    //删除角色
    void deleteById(Long roleId);

    Map<String, Object> findAllRoles(Long userId);
}
