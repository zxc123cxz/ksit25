package com.kaishengit.controller;

import com.kaishengit.eneyit.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class HelloController {


    @GetMapping("/hello")
    public String cnn(){
        return "/customer/hello";
    }


    @PostMapping("/hello")
    public String controller(Customer customer){
        System.out.println(customer.getName()+customer.getAddress());
        System.out.println("123456");

      //  System.out.println("name"+name+"address"+address);
        return "redirect:/customer/hello";
    }

    /*客户端像业务控制器发送数据，业务控制器接受*/
   @GetMapping("/{id:\\d+}/{typeName:\\.}")
    public String save(@PathVariable Integer id,
                        String typeName,
                       Model model){
        System.out.println("id-->"+id);
        model.addAttribute("id",id);
        model.addAttribute("typeName",typeName);
        return "hi";
    }
  /*  @GetMapping("/new")
   public String customer(){
       return "/customer/new";
   }*/

   @GetMapping("/a.jon")
   @ResponseBody
   public String sace(){
       return "文龙";
   }
    @GetMapping("/{id}.jon")
   public @ResponseBody  Customer Viewcust(@PathVariable Integer id){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("刘文龙");
        customer.setAddress("周口");
        return customer;
   }
    @GetMapping("/all.jon")
   public @ResponseBody List<Customer> view(){
        List<Customer> customerList = Arrays.asList(
              new Customer(100,"刘文龙",18,"周口"),
              new Customer(101,"老汉",20,"周口")
        );
        return customerList;
   }

}
