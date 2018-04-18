package com.kaishengit.tms.controller;

import com.google.common.collect.Maps;
import com.kaishengit.tms.PermissionService;
import com.kaishengit.tms.RolesService;
import com.kaishengit.tms.com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/roles")
public class RolesController {



    @Autowired
    private RolesService rolesService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String home(Model model){
        List<Roles> rolesList = rolesService.findAllRolesPermission();
        model.addAttribute("rolesList",rolesList);
        return "manage/roles/home";
    }

    @GetMapping("/new")
    public String New(Model model){
        List<Permission> permissionList = permissionService.findAll();
        model.addAttribute("permissionList",permissionList);

        return "/manage/roles/new";
  }

   @PostMapping("/new")
    public  String New(Roles roles, Integer[] permissionId, RedirectAttributes redirectAttributes){
        System.out.println(roles.getRolesName() + roles.getRolesCode());
        rolesService.save(roles,permissionId);

        redirectAttributes.addFlashAttribute("新增成功");
        return "redirect:/manage/roles";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edit(Model model,
                       @PathVariable Integer id){
        Roles roles = rolesService.findRolesPermission(id);
        model.addAttribute("roles",roles);

        List<Permission> permissionList = permissionService.findAllPermission();

        Map<Permission,Boolean> map = CheckPermission(permissionList,roles.getPermissionList());
        model.addAttribute("permissionMap",map);
        return "manage/roles/edit";
    }
    /*  （check打勾）
     * 查询出角色的权限管理
     * 和查询出所有的权限管理
     * 使用google的Maps的linked 有序迭代
     * 利用镖旗返回给jsp entity.key.id （entity中的map类）判断  角色的权限和权限管理是否相同（Boolean）
     * @date 2018/4/16
     * @param
     * @return
     */
    private Map<Permission,Boolean> CheckPermission(List<Permission> permissionList, List<Permission> permissionList1) {
        Map<Permission,Boolean> resultMap = Maps.newLinkedHashMap();

        for(Permission permission : permissionList){
            boolean flag = false;
            for(Permission permission1 : permissionList1){
                if(permission.getId().equals(permission1.getId())){
                    flag = true;
                    break;
                }
            }
            resultMap.put(permission, flag);
        }
        return resultMap ;
    }

    @PostMapping("/{id:\\d+}/edit")
    public String edit(Roles roles,Integer[] permissionId,RedirectAttributes redirectAttributes){
        rolesService.update(roles,permissionId);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/manger/roles";
    }



    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean del(@PathVariable Integer id, RedirectAttributes redirectAttributes){

        try{
            rolesService.delete(id);
            redirectAttributes.addFlashAttribute("message","删除成功");
            return ResponseBean.success();
        }catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }


    }

}
