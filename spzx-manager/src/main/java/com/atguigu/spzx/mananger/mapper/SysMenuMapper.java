package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface SysMenuMapper {

    //查询所有菜单
    List<SysMenu> selectAll();

    //添加顶级菜单
    void insert(SysMenu sysMenu);

    //修改菜单
    void updateById(SysMenu sysMenu);

    //根据id查询菜单
    int countByParentId(Long id);

    //删除菜单
    void deleteById(Long id);

    //根据用户id查询菜单
    List<SysMenu> selectListByUserId(Long userId);

    //根据父id查询菜单
    SysMenu selectById(Long parentId);
}