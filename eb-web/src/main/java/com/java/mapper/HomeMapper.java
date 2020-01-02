package com.java.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface HomeMapper {
    @Select("select * from web_menu where menuType=#{arg0} order by updateTime limit 8")
    List<Map<String,Object>> selectHxMenuByType(String menuType);
}
