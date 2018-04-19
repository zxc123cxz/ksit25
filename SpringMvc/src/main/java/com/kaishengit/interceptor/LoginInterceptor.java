package com.kaishengit.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor /*extends HandlerInterceptorAdapter*/ implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得请求路径
        String url = request.getRequestURI();
        if(url.startsWith("/static/") || url.equals("favicon.ico") ){
                    return true;
        }

        //首页
        if("".equals(url) || "/".equals(url)){
            return true;
        }

       /* HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
                response.sendRedirect("/");
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
