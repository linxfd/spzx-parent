package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */

@Mapper
public interface SysRoleMenuMapper {

    //根据角色id查询菜单id
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    //根据角色id删除角色菜单关系数据
    void deleteByRoleId(Long roleId);

    //给角色分配菜单权限
    void doAssign(AssginMenuDto assginMenuDto);

    //更新角色菜单权限为半选状态
    void updateSysRoleMenuIsHalf(Long id);
}
