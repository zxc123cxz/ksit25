package com.kaishengit.tms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.Accountservice;
import com.kaishengit.tms.RolesService;
import com.kaishengit.tms.com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.NotFoundException;
import com.kaishengit.tms.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/account")
public class AccountController  {

    @Autowired
    private Accountservice accountservice;

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public String home(@RequestParam(required = false)Integer roles,
                        @RequestParam(required = false)String nameMobile,
                       Model model){
        Map<String,Object> map = Maps.newHashMap();
        map.put("roles",roles);
        map.put("nameMobile",nameMobile);
        //查询的所有acount和roles角色信息
        List<Account> rolesList = accountservice.findAllAccountRoles();

        //查询所有的关于账号和手机号（模糊查询）
        List<Account> accountRolesList = accountservice.findParamType(map);

        model.addAttribute("rolesList",rolesList);
        model.addAttribute("accountRolesList",accountRolesList);
       // PageInfo<Account> accountList = accountservice.findAll(pageNo,map);
       // model.addAttribute("accountList",accountList);
        return "manage/account/home";
    }

   /* 获取 所有的角色
     *
     * @date 2018/4/16
     * @param
     * @return
     */
   @GetMapping("/new")
    public String New(Model model){
        List<Roles> rolesList = rolesService.findAllRoles();
        model.addAttribute("rolesList",rolesList);
        return "manage/account/new";
    }


    /*
     *  获取添加用户的对象
     *  和用户和角色的关联关系表的角色id
     *  一个用户可以拥有（扮演）多个角色
     * @date 2018/4/16
     * @param
     * @return
     */
    @PostMapping("/new")
    public String New(Account account,Integer[] rolesIds){

       accountservice.save(account,rolesIds);
       return "redirect:/manage/account";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edit(Model model,
                       @PathVariable Integer id){

        Account account = accountservice.findByIdAccount(id);
        if(account == null){
            throw new NotFoundException();
        }
        List<Roles> rolesList = rolesService.findAllRoles();
        //当前账号拥有的角色
        List<Roles> accountRolesList = rolesService.findAccountRoles(id);
        model.addAttribute("account",account);
        model.addAttribute("accountRolesList",accountRolesList);
        model.addAttribute("rolesList",rolesList);
        return "manage/account/edit";
    }


    @PostMapping("/{id:\\d+}/edit")
    public String edit(Account account, Integer[] rolesIds, RedirectAttributes redirectAttributes){

        accountservice.update(account,rolesIds);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/manage/account";
    }


    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean del(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        try{
            accountservice.delete(id);
            redirectAttributes.addFlashAttribute("message","删除成功");
            return  ResponseBean.success();
        }catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }

}
