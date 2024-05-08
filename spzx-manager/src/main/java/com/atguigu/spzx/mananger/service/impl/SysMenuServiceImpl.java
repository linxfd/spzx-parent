package com.atguigu.spzx.mananger.service.impl;

import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.mananger.mapper.SysMenuMapper;
import com.atguigu.spzx.mananger.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.mananger.service.SysMenuService;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import com.atguigu.spzx.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper ;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<SysMenu> findNodes() {
        //查询所有菜单
        List<SysMenu> sysMenuList = sysMenuMapper.selectAll() ;
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }
        //构建树形数据
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);
        return treeList;
    }

    @Transactional
    @Override
    public void save(SysMenu sysMenu) {
        // 添加新的节点
        sysMenuMapper.insert(sysMenu) ;

        // 新添加一个菜单，那么此时就需要将该菜单所对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu) ;
    }

    // 递归调用,将菜单所对应的父级菜单设置为半开
    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {
        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.selectById(sysMenu.getParentId());
        if(parentMenu != null) {
            // 将该id的菜单设置为半开
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId()) ;
            // 递归调用
            updateSysRoleMenuIsHalf(parentMenu) ;
        }
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu) ;
    }
    @Override
    public void removeById(Long id) {
        // 先查询是否存在子菜单，如果存在不允许进行删除
        int count = sysMenuMapper.countByParentId(id);
        if (count > 0) {
            throw new GuiguException(ResultCodeEnum.NODE_ERROR);
        }
        // 不存在子菜单直接删除
        sysMenuMapper.deleteById(id);
    }
    @Override
    public List<SysMenuVo> findUserMenuList() {
        // 根据当前登录用户查询菜单,从ThreadLocal变量中获取
        SysUser sysUser = AuthContextUtil.get();
        // 获取当前登录用户的id
        Long userId = sysUser.getId();
        // 根据用户id查询菜单
        List<SysMenu> sysMenuList = sysMenuMapper.selectListByUserId(userId) ;

        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}