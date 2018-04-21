package com.kaishengit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.TicketAccountService;
import com.kaishengit.tms.com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/store")
public class TicketStoreAccountController {

    @Autowired()
    private TicketAccountService ticketAccountService;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false,defaultValue = "") String storeName,
                       @RequestParam(required = false,defaultValue = "") String storeManager,
                       @RequestParam(required = false,defaultValue = "") String storeTel
                       ){
        Map<String,Object> storeMap = Maps.newHashMap();
        storeMap.put("storeName",storeName);
        storeMap.put("storeManager",storeManager);
        storeMap.put("storeTel",storeTel);
        PageInfo<TicketStore> ticketStorePageInfo = ticketAccountService.findPageNoTicketStore(pageNo,storeMap);
        model.addAttribute("pageInfo",ticketStorePageInfo);
        return "store/home";
    }


    @GetMapping("/new")
    public String New(Model model){

        return "store/new";
    }


    @PostMapping("/new")
    public String New(TicketStore ticketStore, RedirectAttributes redirectAttributes){
        ticketAccountService.save(ticketStore);
        redirectAttributes.addFlashAttribute("message","保存成功");
        return "redirect:/store/home";
    }



    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id, Model model){
        TicketStore ticketStore = ticketAccountService.FindTicketStoreById(id);
        model.addAttribute("ticketStore",ticketStore);
        return "store/edit";
    }


    @PostMapping("/{id:\\d+}/edit")
    public String edit(TicketStore ticketStore,RedirectAttributes redirectAttributes){
        ticketAccountService.update(ticketStore);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/store/hoome";
    }




    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean del(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            ticketAccountService.DeleteFindById(id);
            redirectAttributes.addFlashAttribute("message", "删除成功");
            return ResponseBean.success();
        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }





    }

    /*
     *
     * @date 2018/4/20
     * @param   售票点的详情页面
     * @return
     */
     @GetMapping("/{id:\\d+}")
     public String Info(@PathVariable Integer id,Model model){
        TicketStore ticketStore = ticketAccountService.FindTicketStoreById(id);

        StoreAccount storeAccount =ticketAccountService.FindStoreAccount(id);

        model.addAttribute("ticketStore",ticketStore);
        model.addAttribute("storeAccount",storeAccount);

        return "store/info";
     }


    @GetMapping("/{id:\\d+}/lock")
     public @ResponseBody ResponseBean  Lock(@PathVariable Integer id) {
        try {
            ticketAccountService.findByIdLock(id);
            return  ResponseBean.success();
        } catch (ServiceException e) {
            return  ResponseBean.error(e.getMessage());
        }
    }

   /*
     * 恢复售票账号
     * @date 2018/4/20
     * @param
     * @return
     */
    @GetMapping("/{id:\\d+}/recover")
    public @ResponseBody ResponseBean Recover(@PathVariable Integer id) {
        try {
            ticketAccountService.findByIdRocover(id);
            return  ResponseBean.success();
        } catch (ServiceException e) {
            return  ResponseBean.error(e.getMessage());
        }
    }

}
