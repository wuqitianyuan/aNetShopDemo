package com.java.controller;

import com.java.service.LoginService;
import com.java.utils.MD5Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/login.do")
    public String login(String username, String pwd, HttpSession session) throws Exception {
        if(username==null || pwd==null){
            return "login";
        }
        boolean flag1=username.matches(".{3,12}");
        boolean flag2=pwd.matches(".{6,12}");
        if(!flag1||!flag2){
            return "login";
        }
        pwd= MD5Tool.MD5(pwd);
        boolean flag=loginService.LoginSucceed(username,pwd);
        if(flag){
            session.setAttribute("username",username);
        }
        return flag? "index":"login";
    }

    @RequestMapping("/getAuthorityByUsername.do")
    public @ResponseBody List<Map<String,Object>> getAuthorityByUsername(@RequestParam(name="id",defaultValue = "0") Long id, HttpSession session){
        String username = (String)session.getAttribute("username");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("username",username);
        paramMap.put("id",id);
        return loginService.findAuthorityByUsername(paramMap);
    }
}
