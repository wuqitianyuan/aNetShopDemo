package com.java.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WebMenuService {
    Map<String,Object> getMenusByType(Integer startIndex, Integer pageSize);

    int insertMenu(String title, String url);

    int updateMenu(HashMap<String,Object> paramMap);

    int deleteMenu(HashMap<String,Object> paramMap);
}
