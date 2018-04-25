package com.kaishengit.tms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.TicketinRecordService;
import com.kaishengit.tms.com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.TicketInRecord;


import com.kaishengit.tms.exception.ServiceException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


@RequestMapping("/ticket/store")
@Controller
public class ticketController {

    /*
     *  年票入库
     * @date 2018/4/23
     * @param
     * @return
     */
   @Autowired
   private TicketinRecordService ticketinRecordService;

    @GetMapping
    public String home(@RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo, Model model,
                       @RequestParam(required = false,defaultValue = "") String createTime,
                       @RequestParam(required = false,defaultValue = "")String accountName){

        Map<String,Object> ticketInrecord = Maps.newHashMap();
        ticketInrecord.put("createTime",createTime);
        ticketInrecord.put("accountName",accountName);
        PageInfo<TicketInRecord> ticketInRecordPageInfo =  ticketinRecordService.findPageTicketInRecord(pageNo);
       List<TicketInRecord> ticketInRecords = ticketinRecordService.findAllTicketInrecord();

        String today = DateTime.now().toString("YYYY-MM-dd");
        model.addAttribute("today",today);
        model.addAttribute("ticketInRecords",ticketInRecords);
        model.addAttribute("ticketInRecordPageInfo",ticketInRecordPageInfo);
        return "ticket/store/home";
    }




    @GetMapping("/new")
    public String New(Model model){

        String today = DateTime.now().toString("YYYY-MM-dd");
        model.addAttribute("today",today);
        return "ticket/store/new";
    }




    @PostMapping("/new")
    public String New(TicketInRecord ticketInRecord, RedirectAttributes redirectAttributes){

        try{
            ticketinRecordService.save(ticketInRecord);
            redirectAttributes.addFlashAttribute("message","新增成功");

        }catch(ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/ticket/store";
    }



    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id,Model model){
        String today = DateTime.now().toString("YYYY-MM-dd");
        model.addAttribute("today",today);
        TicketInRecord   ticketInRecord = ticketinRecordService.findTicketInRecird(id);
        model.addAttribute("ticketInRecord",ticketInRecord);
        return "ticket/store/edit";
    }



    @PostMapping("/{id:\\d+}/edit")
    public String edit(TicketInRecord ticketInRecord,RedirectAttributes redirectAttributes){
        ticketinRecordService.update(ticketInRecord);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/ticket/store";
    }



    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean del(@PathVariable Integer id,RedirectAttributes redirectAttributes){

        try{
            ticketinRecordService.Delete(id);
            redirectAttributes.addFlashAttribute("message"," 删除成功");
            return ResponseBean.success();
        }catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }


    }




}
