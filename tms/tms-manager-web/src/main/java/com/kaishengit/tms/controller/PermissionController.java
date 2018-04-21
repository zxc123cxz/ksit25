package com.kaishengit.tms.controller;

import com.kaishengit.tms.PermissionService;
import com.kaishengit.tms.com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

        @Autowired
        private PermissionService permissionService;


        /*
         *
         * @date 2018/4/13
         * @param   查询所有peimission类型为菜单的数据
         * @return
         */
        @GetMapping
        public String home(Model model){
            List<Permission> permissionList = permissionService.findAll();
            model.addAttribute("permissionList",permissionList);
            return "manage/permission/home";
        }

        /*
         *
         * @date 2018/4/14
         * @param   进入系统权限管理
         *          查询出（显示出）所有的权限设置（结构）
         * @return
         */
        @GetMapping("/new")
        public String New(Model model){
            List<Permission> permissionList = permissionService.findtype(Permission.MENU);
            model.addAttribute("permissionList",permissionList);
            return "manage/permission/new";
        }

        /*
         *  添加权限
         * @date 2018/4/14
         * @param
         * @return
         */
        @PostMapping("/new")
        public String New(Permission permission,RedirectAttributes redirectAttributes){
            permissionService.PerSave(permission);
            redirectAttributes.addFlashAttribute("message","新权限添加成功");
            return "redirect:/manage/permission";
    }


    /*
     *  删除权限
     * @date 2018/4/15
     * @param
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean delete(@PathVariable Integer id){
        try{
            permissionService.delete(id);
            return ResponseBean.success();
        }catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }


    @GetMapping("/{id:\\d+}/edit")
    public String update(@PathVariable Integer id,Model model){
        System.out.println(id);
        Permission permission = permissionService.findById(id);
        List<Permission> permissionList = permissionService.findAll();
        model.addAttribute("permission",permission);
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String  update(Permission permission,RedirectAttributes redirectAttributes){

            permissionService.update(permission);
            redirectAttributes.addFlashAttribute("message","修改成功");
            //return ResponseBean.success();
        return "redirect:/manage/permission";
    }


}
