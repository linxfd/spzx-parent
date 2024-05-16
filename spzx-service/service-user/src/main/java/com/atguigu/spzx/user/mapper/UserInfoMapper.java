package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
 * @version 1.0
 */
@Mapper
public interface UserInfoMapper {
    void save(UserInfo userInfo);

    UserInfo getByUsername(@Param("username") String username);
}
