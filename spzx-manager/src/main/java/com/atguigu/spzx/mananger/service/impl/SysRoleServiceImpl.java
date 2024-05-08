package com.atguigu.spzx.mananger.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mananger.mapper.SysRoleMapper;
import com.atguigu.spzx.mananger.mapper.SysRoleUserMapper;
import com.atguigu.spzx.mananger.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper ;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper ;
    /**
     * 分页查询
     * @param current 当前页码
     * @param limit 每页显示的条数
     * @param sysRoleDto 查询条件
     * @return PageInfo<SysRole>  是 一个java自带工具类，封装了分页数据
     */
    @Override
    public PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto) {
        // 设置分页参数 current是页码，limit是每页显示的条数
        PageHelper.startPage(current, limit);
        // 查询数据
        List<SysRole> sysRoleList =  sysRoleMapper.findByPage(sysRoleDto);
        // 封装分页数据
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleList);
        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        /**
         * 时间的更新都在数据库约束了
         * `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         * `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
         *`is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:可用 1:不可用）',
         */
        SysRole role= sysRoleMapper.queryByRoleName(sysRole.getRoleName()) ;
        if (role != null ) {
            throw new GuiguException(ResultCodeEnum.ROLE_NAME_IS_EXISTS);
        }
        sysRoleMapper.saveSysRole(sysRole);

    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        SysRole role= sysRoleMapper.queryByRoleName(sysRole.getRoleName()) ;
        if (role != null ) {
            throw new GuiguException(ResultCodeEnum.ROLE_NAME_IS_EXISTS);
        }
        sysRoleMapper.updateSysRole(sysRole) ;
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId) ;
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        // 查询所有的角色数据
        List<SysRole> sysRoleList = sysRoleMapper.findAllRoles() ;

        // 查询当前登录用户的角色数据
        List<Long> sysRoles = sysRoleUserMapper.findSysUserRoleByUserId(userId);

        Map<String , Object> resultMap = new HashMap<>() ;
        resultMap.put("allRolesList" , sysRoleList) ;
        resultMap.put("sysUserRoles", sysRoles);
        return resultMap;
    }
}
