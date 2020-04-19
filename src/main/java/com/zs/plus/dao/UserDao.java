package com.zs.plus.dao;

import com.zs.plus.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
  
  @Select("select * from user where id = #{id}")
  public User getById(@Param("id") int id);
  
  @Select("insert into user(name) values (#{name})")
  public User insert(@Param("user") User user);
  
}
