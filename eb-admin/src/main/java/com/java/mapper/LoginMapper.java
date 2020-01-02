package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface LoginMapper {
    @Select("select count(*) from admin_users where username=#{arg0} and pwd=#{arg1}")
    int getLoginUser(String username, String pwd);

    List<Map<String,Object>> getAuthorityByUsername(Map<String,Object> paramMap);

}
