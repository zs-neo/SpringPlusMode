package com.zs.plus.service;

import com.zs.plus.domain.po.MUser;
import com.zs.plus.domain.po.OrderInfo;
import com.zs.plus.domain.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MService {
  
  @Autowired
  GoodsService goodsService;
  
  @Autowired
  OrderService orderService;
  
  @Transactional
  public OrderInfo miaosha(MUser mUser, GoodsVO goodsVO){
    goodsService.reduceStock(goodsVO);
    return orderService.createOrder(mUser,goodsVO);
  }

}
