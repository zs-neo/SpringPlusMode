package com.zs.plus.dao;

import com.zs.plus.domain.po.MOrder;
import com.zs.plus.domain.po.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {
  
  @Select("select * from morder where user_id=#{userId} and goods_id=#{goodsId}")
  public MOrder getMOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);
  
  @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
    + "#{user_id}, #{goods_id}, #{goods_name}, #{goods_count}, #{goods_price}, #{order_channel},#{status},#{create_date} )")
  @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
  public long insert(OrderInfo orderInfo);
  
  @Insert("insert into morder (user_id, goods_id, order_id)values(#{user_id}, #{goods_id}, #{order_id})")
  public int insertMOrder(MOrder mOrder);
  
  
}
