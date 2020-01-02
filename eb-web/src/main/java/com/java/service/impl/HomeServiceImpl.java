package com.java.service.impl;

import com.java.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CacheConfig(cacheNames = {"HomeServiceImpl_"})
@Service
public class HomeServiceImpl implements com.java.service.HomeService {
    @Autowired
    private HomeMapper homeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis编程式开发
     * @param menuType
     * @return
     */
    @Override
    public List<Map<String,Object>> getHxMenuByType(String menuType){
        try{
            //查看redis缓存数据库中是否存在相应数据
            ValueOperations ops = redisTemplate.opsForValue();
            Object obj = ops.get("hxMenus");
            //不存在则查询mysql并将记录存入redis缓存数据库
            if(obj==null){
                List<Map<String,Object>> menuList = homeMapper.selectHxMenuByType(menuType);
                ops.set("hxMenus",menuList);
                redisTemplate.expire("hxMenus",1, TimeUnit.MINUTES);
                return menuList;
            }
            //存在则直接返回redis缓存数据库中的记录
            return (List<Map<String,Object>>)obj;
        }catch(Exception e){
            List<Map<String,Object>> menuList = homeMapper.selectHxMenuByType(menuType);
            System.err.println("HomeServiceImpl.getHxMenuByType......Redis未开启！");
            return menuList;
        }

    }

    /**
     * redis注解师开发：
     * eg:@Cacheable(key="'hxMenu'")
     * Cacheable注解会自动去redis数据库查找是否存在key为hxMenu的值，存在则直接返回
     * 不存在则存入再返回，key值一定要加上单引号
     *
     *  CacheEvict(key="'hxMenu'")
     *  CacheEvict：用于清空 redis数据库中key的数据
     */
}
