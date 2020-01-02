package com.java.service.impl;

import com.java.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements com.java.service.LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Boolean LoginSucceed(String username, String pwd){
        return loginMapper.getLoginUser(username, pwd) > 0;
    }

    @Override
    public List<Map<String, Object>> findAuthorityByUsername(Map<String, Object> paramMap) {
        return loginMapper.getAuthorityByUsername(paramMap);
    }
}
