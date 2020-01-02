package com.java.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WebMenuMapper {
    @Select("select * from web_menu where menuType='1' limit #{arg0}, #{arg1}")
    List<Map<String,Object>> selectMenusByType (Integer startIndex,Integer pageSize);

    @Select("select count(*) from web_menu where menuType='1'")
    int selectAllMenus();

    @Insert("insert into web_menu values(null,#{arg0},#{arg1},1,now())")
    int insertMenu(String title, String url);

    @Update("update web_menu set title=#{title}, url=#{url}, updateTime=now() where id=#{id}")
    int updateMenu(HashMap<String,Object> paramMap);

    @Delete("delete from web_menu where id in (${ids})")
    int deleteMenu(HashMap<String,Object> paramMap);
}
