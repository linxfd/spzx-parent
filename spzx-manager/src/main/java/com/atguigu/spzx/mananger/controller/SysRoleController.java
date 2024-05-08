package com.atguigu.spzx.mananger.controller;

import com.atguigu.spzx.mananger.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 分页查询接口
     * @param current 当前页码
     * @param limit 每页显示条数
     * @param sysRoleDto
     * @return
     */
    @Operation(summary = "分页查询接口")
    @PostMapping(value = "/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto ) {
        PageInfo<SysRole> pageInfo =  sysRoleService.findByPage(current,limit,sysRoleDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 新增角色接口
     * @param SysRole
     * @return
     */
    @Operation(summary = "新增角色接口")
    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole SysRole) {
        sysRoleService.saveSysRole(SysRole) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "修改角色接口")
    @PutMapping(value = "/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }


    @Operation(summary = "删除角色接口")
    @DeleteMapping(value = "/deleteById/{roleId}")
    public Result deleteById(@PathVariable(value = "roleId") Long roleId) {
        sysRoleService.deleteById(roleId) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 给用户分配角色时，查询所有角色接口
     * Map中allRolesList键中的值是查询所有角色接口
     * Map中sysUserRoles键中的值是查询用户已分配角色接口
     * @return
     */
    @Operation(summary = "查询所有角色接口")
    @GetMapping(value = "/findAllRoles/{userId}")
    public Result<Map<String , Object>> findAllRoles(@PathVariable(value = "userId") Long userId) {
        Map<String, Object> resultMap = sysRoleService.findAllRoles(userId);
        return Result.build(resultMap , ResultCodeEnum.SUCCESS)  ;
    }

}
