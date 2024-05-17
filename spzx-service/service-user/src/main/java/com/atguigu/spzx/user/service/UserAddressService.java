package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * @version 1.0
 */
public interface UserAddressService {

    // 查询用户地址列表
    List<UserAddress> findUserAddressList();

    // 根据id查询用户地址
    UserAddress getById(Long id);
}
