package com.zs.plus.dao;

import com.zs.plus.domain.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MUserDao {
  
  @Select("select * from muser where id = #{id}")
  public MUser getById(@Param("id") long id);
  
}
