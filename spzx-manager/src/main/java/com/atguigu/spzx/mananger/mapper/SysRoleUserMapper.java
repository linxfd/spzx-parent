package com.atguigu.spzx.mananger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface SysRoleUserMapper {

    // 添加关联关系
    public abstract void doAssign(@Param("userId") Long userId,
                                  @Param("roleId") Long roleId);

    // 根据用户的id删除数据
    public abstract void deleteByUserId(Long userId);

    // 根据用户的id查询用户的角色id
    List<Long> findSysUserRoleByUserId(Long userId);
}