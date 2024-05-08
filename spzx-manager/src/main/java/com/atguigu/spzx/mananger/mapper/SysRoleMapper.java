package com.atguigu.spzx.mananger.mapper;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface SysRoleMapper {

    //根据条件分页查询
    List<SysRole> findByPage(SysRoleDto sysRoleDto);



   void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    //根据角色名称查询
    @Select("select * from sys_role where role_name = #{roleName}")
    SysRole queryByRoleName(String roleName);


    @Update("update sys_role set is_deleted = 1 where id = #{roleId}")
    void deleteById(Long roleId);

    //查询所有角色
    List<SysRole> findAllRoles();

}
