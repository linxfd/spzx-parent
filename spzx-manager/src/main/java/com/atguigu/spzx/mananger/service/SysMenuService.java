package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @version 1.0
 */
public interface SysMenuService {

    //查询所有菜单
    List<SysMenu> findNodes();

    //添加顶级菜单
    void save(SysMenu sysMenu);

    //修改菜单
    void updateById(SysMenu sysMenu);

    //删除菜单
    void removeById(Long id);

    //根据用户id查询菜单
    List<SysMenuVo> findUserMenuList();

}
