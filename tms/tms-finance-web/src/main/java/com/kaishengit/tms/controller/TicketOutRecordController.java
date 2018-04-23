package com.kaishengit.tms.controller;


import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.TicketAccountService;
import com.kaishengit.tms.TicketOutRecordService;
import com.kaishengit.tms.com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.krb5.internal.Ticket;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ticket")
public class TicketOutRecordController {


    @Autowired
    private TicketOutRecordService ticketOutRecordService;

    @Autowired
    private TicketAccountService ticketAccountService;

    /*年票下发
     *
     * @date 2018/4/23
     * @param
     * @return
     */

    @GetMapping("/out/home")
    public String home(Model model,
                       @RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo){
        PageInfo<TicketOutRecord> ticketOutRecordPageInfo = ticketOutRecordService.findAllTicketOutRecord(pageNo);

        model.addAttribute("ticketOutRecordPageInfo",ticketOutRecordPageInfo);
        return "ticket/out/home";
    }



    @GetMapping("/out/new")
    public String New(Model model){
        String today = DateTime.now().toString("YYYY-MM-dd");
        List<TicketStore> ticketStoreList = ticketAccountService.findAllTicketStore();

        model.addAttribute("ticketStoreList",ticketStoreList);
        model.addAttribute("today",today);
        return "ticket/out/new";
    }


    @PostMapping("/out/new")
    public String New(TicketOutRecord ticketOutRecord, RedirectAttributes redirectAttributes){
        try{

            ticketOutRecordService.save(ticketOutRecord);
            redirectAttributes.addFlashAttribute("message","添加成功");

        }catch (ServiceException e){
            throw  new ServiceException(e.getMessage());
        }

           return "redirect:/ticket/out/home";
    }



    @GetMapping("/out/{id:\\d+}/del")
    public @ResponseBody ResponseBean  del(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        try{

            ticketOutRecordService.delete(id);
            redirectAttributes.addFlashAttribute("删除成功");
            return ResponseBean.success();
        }catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }


    /*
     *  查询盘点统计
     * @date 2018/4/23
     * @param
     * @return
     */
    @GetMapping("/chart/home")
    public String Chart(Model model){
        Map<String,Long> map =  ticketAccountService.findCountTicket();
        model.addAttribute("map",map);
        return "ticket/chart/home";
    }

}
