package com.maven.packagetest.service;

import com.maven.packagetest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<HashMap<String, Object>> getAll() {
        return userMapper.getAll();
    }

    @Cacheable( value = "userInfo", key = "#root.targetClass+':getUserById:'+#id" )
    public HashMap<String, Object> getUserById(Integer id) {
        return userMapper.getUserById( id );
    }

    @CacheEvict( value = "userInfo", key = "#root.targetClass+':getUserById:'+#id" )
    @Transactional( rollbackFor = Exception.class )
    public void updateUserById(HashMap<String, Object> map, Integer id) {
        userMapper.updateUserById( map );
    }


}
