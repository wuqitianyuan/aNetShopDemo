package com.java.controller;

import com.java.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

    @RequestMapping("/getHxMenus.do")
    public @ResponseBody List<Map<String,Object>> getHxMenus(){
        return homeService.getHxMenuByType("1");
    }
}
