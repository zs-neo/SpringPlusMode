package com.zs.plus.service;

import com.zs.plus.dao.GoodsDao;
import com.zs.plus.domain.po.MGoods;
import com.zs.plus.domain.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

  @Autowired
  private GoodsDao goodsDao;

  public List<GoodsVO> listGoodsVO(){
    return goodsDao.listGoodsVo();
  }
  
  public GoodsVO getGoodsVoByGoodsId(long goodsId) {
    return goodsDao.getGoodsVoByGoodsId(goodsId);
  }
  
  public void reduceStock(GoodsVO goods) {
    MGoods g = new MGoods();
    g.setId(goods.getId());
    goodsDao.reduceStock(g);
  }
  
}
