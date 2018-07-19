package com.maven.packagetest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<HashMap<String,Object>> getAll();

    HashMap<String,Object> getUserById(Integer id);

    void updateUserById(HashMap<String,Object> map);
}
