package com.kaishengit.tms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index(){
        Subject subject = SecurityUtils.getSubject();

        // 如果被认证了则退出登陆
        if(subject.isAuthenticated()){
            subject.logout();
        }

        //如果被记住了
        if(subject.isRemembered()){
            return "/home";
        }

        return "/index";
    }

    /**
     * 系统登录
     * @return
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String password,
                        String rememberMe,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        // 创建Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 根据账号和密码进行登录
        String requestIp = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(accountMobile,password,rememberMe!=null,requestIp);

        try {
            subject.login(usernamePasswordToken);
                //根据角色判断
            if(subject.hasRole("finance") || subject.hasRole("store")) {
                //登录后跳转目标的判断
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                String url = "/home";
                if(savedRequest != null) {
                    url = savedRequest.getRequestUrl();
                }

                return "redirect:"+url;
            } else {
                logger.info("{} 没有权限登录该系统",accountMobile);
                redirectAttributes.addFlashAttribute("message","没有登录该系统的权限");
            }
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号被锁定");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        }
        return "redirect:/";
    }

    /**
     * 登录后的首页
     * @return
     */
    @GetMapping("/home")
    public String home() {

        return "home";
    }

    /**
     * 安全退出
     * @return
     */
    /*@GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        redirectAttributes.addFlashAttribute("message","你已安全退出系统");
        return "redirect:/";
    }*/

    @GetMapping("/401")
    public String unauthorizedUrl() {
        return "error/401";
    }


}
