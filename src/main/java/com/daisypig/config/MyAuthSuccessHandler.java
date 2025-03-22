package com.daisypig.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();//获取认证成功的用户信息
        //Object credentials = authentication.getCredentials();//获取认证成功的密码信息
        Object details = authentication.getDetails();//获取认证成功的详细信息
        //Object authorities = authentication.getAuthorities();//获取认证成功的权限信息
        HashMap<Object, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("msg", "登录成功");
        result.put("data", principal);
        String jsonString = JSON.toJSONString(result);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(jsonString);
    }
}
