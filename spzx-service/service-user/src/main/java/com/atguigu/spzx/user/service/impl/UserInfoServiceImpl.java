package com.atguigu.spzx.user.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.model.dto.h5.UserLoginDto;
import com.atguigu.spzx.model.dto.h5.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterDto userRegisterDto) {

        // 获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //校验参数
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(nickName) ||
                StringUtils.isEmpty(code)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        //校验校验 验证码
        String codeValueRedis = redisTemplate.opsForValue().get("phone:code:" + username);
        if(!code.equals(codeValueRedis)) {
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //校验用户名是否存在
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null != userInfo) {
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //保存用户信息
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.save(userInfo);

        // 删除Redis中的数据
        redisTemplate.delete("phone:code:" + username);

    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        //校验用户名
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if(null == userInfo) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        String md5InputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5InputPassword.equals(userInfo.getPassword())) {
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new GuiguException(ResultCodeEnum.ACCOUNT_STOP);
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //userInfo保存到Redis中
        redisTemplate.opsForValue().set("user:spzx:" + token, JSON.toJSONString(userInfo),
                30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        String userInfoJSON = redisTemplate.opsForValue().get("user:spzx:" + token);
        if(StringUtils.isEmpty(userInfoJSON)) {
            throw new GuiguException(ResultCodeEnum.LOGIN_AUTH) ;
        }
        UserInfo userInfo = JSON.parseObject(userInfoJSON , UserInfo.class) ;
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo ;
    }
}
