package com.java.controller;

import com.java.service.WebMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebMenuController {
    @Autowired
    private WebMenuService webMenuService;

    public boolean validChecks(String title, String url){
        //1、校验title、url是否满足格式要求
        if(title==null || url==null){
            return false;
        }
        //2、满足格式要求才调用业务层
        boolean flag1 = title.matches(".{1,10}");
        boolean flag2 = url.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
        if(!flag1 || !flag2){
            return false;
        }
        return true;
    }

    @RequestMapping("/getHxMenus.do")
    public @ResponseBody Map<String,Object> findAllMenus( Integer page, Integer rows){
        System.out.println("page=" + page);
        System.out.println("rows=" + rows);
        int pageSize = page*rows;
        int startIndex = (page - 1)*rows;
        return webMenuService.getMenusByType(startIndex,pageSize);
    }

    @RequestMapping("/addWebMenu.do")
    public @ResponseBody boolean addMenu(String title, String url){
        boolean flag1=title.matches(".{1,12}");
        return webMenuService.insertMenu(title, url) >0;
    }

    @RequestMapping("/modWebMenu.do")
    public @ResponseBody boolean modMenu(String title, String url, Long id){
        boolean checked= validChecks(title,url);
        boolean updated=false;
        if(checked){
            HashMap<String,Object> paramMap = new HashMap<>();
            paramMap.put("title", title);
            paramMap.put("url", url);
            paramMap.put("id",id);
            updated = webMenuService.updateMenu(paramMap) >0;
        }
        return checked&&updated;
    }

    @RequestMapping("/delWebMenu.do")
    public @ResponseBody boolean delMenu(String ids){
        System.out.println("ids=" + ids);
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("ids",ids);
        return webMenuService.deleteMenu(paramMap)>0;
    }
}
