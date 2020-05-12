package com.swd.websiteadmin.Configuration;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        得到session
        HttpSession session = httpServletRequest.getSession(true);
//        得到对象
        Object admin = session.getAttribute("admin");
//        判断对象是否存在
        if(admin!=null){
            return true;
        }else{
//            不存在则跳转到登录页
            httpServletResponse.sendRedirect(httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+"/manage");
            return false;
        }
    }

    //    在请求处理之后,视图渲染之前执行
}
