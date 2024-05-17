package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0
 */
@Mapper
public interface UserAddressMapper {

    // 根据用户id查询用户地址列表
    List<UserAddress> findByUserId(Long userId);

    // 根据id查询用户地址
    UserAddress getById(Long id);
}