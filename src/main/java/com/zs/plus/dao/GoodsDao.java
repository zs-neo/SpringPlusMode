package com.zs.plus.dao;

import com.zs.plus.domain.po.MGoods;
import com.zs.plus.domain.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {
  
  @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.m_price from mgoods mg left join goods g on mg.goods_id = g.id")
  public List<GoodsVO> listGoodsVo();
  
  @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.m_price from mgoods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
  public GoodsVO getGoodsVoByGoodsId(@Param("goodsId")long goodsId);
  
  @Update("update mgoods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
  public int reduceStock(MGoods g);

}
