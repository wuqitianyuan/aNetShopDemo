package com.java.service.impl;

import com.java.mapper.WebMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebMenuServiceImpl implements com.java.service.WebMenuService {
    @Autowired
    private WebMenuMapper webMenuMapper;

    @Transactional(readOnly = false)
    @Override
    public Map<String, Object> getMenusByType(Integer startIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String,Object>> listMap = webMenuMapper.selectMenusByType(startIndex,pageSize);
        int nCount = webMenuMapper.selectAllMenus();
        resultMap.put("rows",listMap);
        resultMap.put("total", nCount);
        return resultMap;
    }

    @Override
    public int insertMenu(String title, String url) {
        return webMenuMapper.insertMenu(title, url);
    }

    @Override
    public int updateMenu(HashMap<String, Object> paramMap) {
        return webMenuMapper.updateMenu(paramMap);
    }

    @Override
    public int deleteMenu(HashMap<String, Object> paramMap) {
        return webMenuMapper.deleteMenu(paramMap);
    }

}
