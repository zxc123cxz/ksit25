package com.kaishengit.tms.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.TicketOutRecordService;
import com.kaishengit.tms.entity.TicketOutRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/finance/ticket")
public class FinanceController {

     @Autowired
     private  TicketOutRecordService ticketOutRecordService;
    /*
     *  售票点缴费
     * @date 2018/4/24
     * @param
     * @return
     */

    @GetMapping("/home")
    public String Home(@RequestParam(name="p",required = false,defaultValue = "1") Integer pageNo,
                       Model model,
                       @RequestParam(required = false,defaultValue = "未支付")String state){
        Map<String,Object>  map = Maps.newHashMap();
        map.put("state",state);

        PageInfo<TicketOutRecord> pageInfo = ticketOutRecordService.findPageTicketOutRecors(map,pageNo);

        model.addAttribute("pageInfo",pageInfo);
        return "finance/ticket/home";
    }


    @GetMapping("/{id:\\d+}/pay")
    public String Pay(@PathVariable Integer id,Model model){
        TicketOutRecord ticketOutRecord =  ticketOutRecordService.findAllTicketOutRecordById(id);

        model.addAttribute("ticketOutRecord",ticketOutRecord);
        return "finance/ticket/pay";
    }


    @PostMapping("/{id:\\d+}/pay")
    public String Pay(@PathVariable Integer id, String payType, RedirectAttributes redirectAttributes){
        ticketOutRecordService.update(id,payType);

        redirectAttributes.addFlashAttribute("message","支付成功");

        return "redirect:/finance/ticket/home";
    }


}
