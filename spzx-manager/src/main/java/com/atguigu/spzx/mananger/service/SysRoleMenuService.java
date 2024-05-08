package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

/**
 * @version 1.0
 */
public interface SysRoleMenuService {

    // 根据角色id查询菜单
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    // 根据角色id更新菜单
    void doAssign(AssginMenuDto assginMenuDto);

}
