package com.kaishengit.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaType;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/kaola")
public class KaolaController {
    @Autowired
    private KaolaService kaolaService;


    @GetMapping("/{id:\\d+}")
    public String findById(@PathVariable Integer id, Model model) throws NotFoundException {
        Kaola kaola = kaolaService.findById(id);
        if(kaola == null){
            throw new NotFoundException();
        }
        model.addAttribute("kaola",kaola);
        return "kaola/kao";
    }

   @GetMapping
    public String findAllPageNo(@RequestParam(defaultValue = "1",name = "p",required = false)Integer pageNo,
                                @RequestParam(required = false) String productName,
                                @RequestParam(required = false) String place,
                                @RequestParam(required = false) Float minPrice,
                                @RequestParam(required = false) Float maxPrice,
                                @RequestParam(required = false) Integer typeId,
                                Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("productName",productName);
        map.put("place",place);
        map.put("minPrice",minPrice);
        map.put("maxPrice",maxPrice);
        map.put("typeId",typeId);
        PageInfo<Kaola> pageInfo = kaolaService.findAllPagedMAP(pageNo,map);
        List<KaolaType> kaolaType = kaolaService.findType();
       // PageInfo<Kaola> pageInfo = kaolaService.findAllPageNo(pageNo);
        System.out.println("pageInfo = " + pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("kaolaType",kaolaType);
       return "kaola/list";
    }

   /* @GetMapping("/all")
    public String findAll(Model model){
       List<Kaola> kaolaList  = kaolaService.findAll();
       model.addAttribute("kaolaList",kaolaList);
       return  "kaola/all";
    }*/
    @GetMapping("/{id:\\d+}/del")
    public String delete(@PathVariable("id")Integer id, RedirectAttributes redirectAttributes){
        kaolaService.findIdDelete(id);
        redirectAttributes.addFlashAttribute("message","商品删除成功");
        return "redirect:/kaola";
    }
    /*保存商品*/
    @PostMapping("/new")
    public String save(Kaola kaola,RedirectAttributes redirectAttributes){
        kaolaService.save(kaola);
        redirectAttributes.addFlashAttribute("message"," 保存成功");
        return "redirect:/kaola";
    }
    /* 获取所有kaolaType商品*/
    @GetMapping("/new")
    public String findType(Model model,RedirectAttributes redirectAttributes){
       List<KaolaType> kaolaTypeList = kaolaService.findType();
        model.addAttribute("kaolaTypeList",kaolaTypeList);
        return "kaola/new";
    }


    /*根据id获取商品内容*/
    @GetMapping("/{id:\\d+}/edit")
    public String findBYID(@PathVariable Integer id,Model model) throws NotFoundException {
       Kaola kaola =  kaolaService.findById(id);
       if (kaola == null){
           throw  new NotFoundException();
        }
        List<KaolaType> kaolaTypeList = kaolaService.findType();

        model.addAttribute("kaola",kaola);

        model.addAttribute("kaolaTypeList",kaolaTypeList);
        return "kaola/edit";
    }


    @PostMapping("/{id:\\d+}/edit")
    public String update(Kaola kaola,RedirectAttributes redirectAttributes){
         kaolaService.updade(kaola);

        redirectAttributes.addFlashAttribute("修改成功");
        return "redirect:/kaola";
    }

}
