package com.kaishengit.tms.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ControllerAdviceHander {

    @ExceptionHandler(org.apache.shiro.authz.UnauthorizedException.class)
    public String unauthorizedHander(){
        return "/error/401";
    }
}
