package com.lening.daylast.controller;

import com.lening.daylast.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/main")
public class MainController {
    @Resource
    private GoodsService service;
    @RequestMapping("/toIndex")
    public String index(){
        return "goods_list";
    }

}
