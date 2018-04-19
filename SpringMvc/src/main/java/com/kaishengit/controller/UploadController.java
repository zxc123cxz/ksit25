package com.kaishengit.controller;

import com.kaishengit.controller.exception.NotFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String upload(@RequestHeader(name="User-Agent")String userAgent, HttpServletRequest request, HttpServletResponse response,
                         HttpSession session){
        System.out.println(userAgent);
        Cookie cookie = new Cookie("user","vip");
        cookie.setHttpOnly(true); // 设置是否支持HttpOnly的属性vip
        cookie.setDomain("localhost");
        cookie.setMaxAge(60*60*24);

        response.addCookie(cookie);
        return "upload";
    }
    @PostMapping("/upload")
    public String uploadFile(@CookieValue String user, String name, MultipartFile photo, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
       /* System.out.println("Hello");
        System.out.println("" + photo.getName());
        System.out.println(photo.getName());

        System.out.println("name-->" + name + "photo-->" + photo);*/
        /*Cookie[] cookies  = request.getCookies();*/
       /* for(Cookie cookie : cookies){
            System.out.println(cookie.getName());
        }*/
       if(!photo.isEmpty()){

           System.out.println("valaue-->"+ user);

           System.out.println(FileUtils.byteCountToDisplaySize(photo.getSize()));
           System.out.println("name属性值--》"+ photo);
           System.out.println("photo文件名" + photo.getOriginalFilename());
           photo.transferTo(new File("x://download/"+ photo.getOriginalFilename()));


       }else{
           redirectAttributes.addFlashAttribute("message","请选择文件");
       }
        return "redirect:/upload";
    }
    @GetMapping("/hi")
    public String MM(){
        return "hi";
    }

    @GetMapping("/{id:\\d+}")
    public  String ThrowException(@PathVariable(name = "id") Integer id, Model model,HttpServletResponse response) throws IOException {

           if(id==101){
              throw new NotFoundException();
             /*  response.sendError(404);
               return null;*/
           }
             System.out.println("id--->" + id);
             model.addAttribute("id",id);
             return "view";
    }

   /* @ExceptionHandler(IOException.class)
    public String ExceptionHandler(){
        return "error/500";
    }*/

}
