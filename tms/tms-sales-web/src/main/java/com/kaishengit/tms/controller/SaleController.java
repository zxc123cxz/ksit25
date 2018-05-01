package com.kaishengit.tms.controller;


import com.kaishengit.tms.CustomerService;
import com.kaishengit.tms.entity.Customer;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.shiro.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/ticket/sales")
public class SaleController {

    @Autowired
    private CustomerService customerService;


    @Autowired
    private ShiroUtil shiroUtil;

    @GetMapping
    public String New(){

        return "ticket/sales";
    }


    @PostMapping("/sales")
    public String New(Customer customer,
                      String ticketNum,
                      BigDecimal price,
                      Model model){
        customerService.save(customer);

        //获取当前售票点
        TicketStore ticketStore = shiroUtil.getCurrentAccount();

        try{
            customerService.saveCustomer(customer,ticketNum,price,ticketStore);
        }catch (ServiceException e){

        }





        return "redirect:/ticket/sales";


    }

}



