package com.atguigu.spzx.mananger.service;

import com.atguigu.spzx.model.vo.system.ValidateCodeVo;

/**
 * @version 1.0
 */
public interface ValidateCodeService {

    // 获取验证码图片
    ValidateCodeVo generateValidateCode();
}
