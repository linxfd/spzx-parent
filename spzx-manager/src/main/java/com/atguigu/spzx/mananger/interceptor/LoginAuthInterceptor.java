package com.atguigu.spzx.mananger.interceptor;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    // 在请求之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方式
        String method = request.getMethod();
        if("OPTIONS".equals(method)) {  // 如果是跨域预检请求，直接放行
            return true ;
        }

        // 获取请求头中的token
        String token = request.getHeader("token");
        // 判断token是否为空
        if (StringUtils.isEmpty(token)) {
            // 响应208状态码给前端
            responseNoLoginInfo(response);
            return false;
        }

        // 如果token不为空，那么此时验证token的合法性
        String sysUserInfoJson = redisTemplate.opsForValue().get("user:login:" + token);
        if (StringUtils.isEmpty(sysUserInfoJson)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 将用户信息放入ThreadLocal中
        SysUser sysUser = JSON.parseObject(sysUserInfoJson, SysUser.class);
        AuthContextUtil.set(sysUser);
        // 更新redis中的token的有效期
        redisTemplate.expire("user:login:" + token, 30, TimeUnit.MINUTES);
        // 放行
        return true;
    }

    // 请求之后执行
    @Override
    public void afterCompletion(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除ThreadLocal中的用户信息
        AuthContextUtil.remove();
    }


    //响应208状态码给前端，因为拦截器不在全局处理器的范围内，所以要单独写
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
