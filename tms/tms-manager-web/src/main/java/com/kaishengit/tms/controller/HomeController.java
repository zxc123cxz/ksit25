package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.Accountservice;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index(){
        Subject subject = SecurityUtils.getSubject();

        //判断登陆是否被认证，如果认证了 退出
        if(subject.isAuthenticated()){
            subject.logout();
        }

        //判断登陆是否记住（remembered(),cookie可见），如果记住则调到首页

        if(subject.isRemembered()){
            return "redirect:/home";
        }
        return "index";
    }

   /*
     *
     * @date 2018/4/12
     * @param   accountMobile 电话号码 accountPassword 密码
     *          requestIp 获取ip地址 session
     *
     * @return
     *      如果验证成功 则把查找的对象set到serssion中
     *      如果失败则redirectAttributes响应给客户端 错误提醒
     *
     *
     *
     */
    @PostMapping("/")
    public String AccountLogin(String accountMobile,
                               String password,
                               HttpServletRequest request,
                               String rememberMe,
                               RedirectAttributes redirectAttributes) {
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();

        //根据账号和密码登陆
        String requestId = request.getRemoteAddr();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile,password,rememberMe!=null,requestId);
        try{
            subject.login(usernamePasswordToken);

            //登陆后的判断跳转
            //WebUtils.getSaveRequnst(requset) 是保存url地址的一个地址 需要SaveRequest接受
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/home";

            if(savedRequest != null){
                url = savedRequest.getRequestUrl();
            }
            return "redirect:"+url;
        }catch (UnknownAccountException | IncorrectCredentialsException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误1");
        }catch (LockedAccountException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号被锁定");
        }catch(AuthenticationException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误2");
        }
            return "redirect:/";
    }

    @GetMapping("/home")
    public String home(){
         return "home";
    }

    @GetMapping("/401")
    public String error(){

        return "/error/401";
    }

}
