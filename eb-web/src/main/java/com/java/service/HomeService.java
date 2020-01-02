package com.java.service;

import java.util.List;
import java.util.Map;

public interface HomeService {
    List<Map<String,Object>> getHxMenuByType(String menuType);
}
