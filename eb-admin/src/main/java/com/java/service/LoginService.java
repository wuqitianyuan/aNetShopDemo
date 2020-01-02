package com.java.service;

import java.util.List;
import java.util.Map;

public interface LoginService {
    Boolean LoginSucceed(String username, String pwd);

    List<Map<String, Object>> findAuthorityByUsername(Map<String, Object> paramMap);
}
