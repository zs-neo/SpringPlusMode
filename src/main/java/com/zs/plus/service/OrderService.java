package com.zs.plus.service;

import com.zs.plus.dao.OrderDao;
import com.zs.plus.domain.po.MOrder;
import com.zs.plus.domain.po.MUser;
import com.zs.plus.domain.po.OrderInfo;
import com.zs.plus.domain.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
  
  @Autowired
  private OrderDao orderDao;
  
  public MOrder getMOrderById(long userId, long goodsId) {
    return orderDao.getMOrderByUserIdGoodsId(userId, goodsId);
  }
  
  public OrderInfo createOrder(MUser user, GoodsVO goods) {
    OrderInfo orderInfo = new OrderInfo();
    orderInfo.setCreate_date(new Date());
    orderInfo.setDelivery_addr_id(0L);
    orderInfo.setGoods_count(1);
    orderInfo.setGoods_id(goods.getId());
    orderInfo.setGoods_name(goods.getGoods_name());
    orderInfo.setGoods_price(goods.getM_Price());
    orderInfo.setOrder_channel(1);
    orderInfo.setStatus(0);
    orderInfo.setUser_id(user.getId());
    long orderId = orderDao.insert(orderInfo);
    MOrder mOrder = new MOrder();
    mOrder.setGoods_id(goods.getId());
    mOrder.setOrder_id(orderId);
    mOrder.setUser_id(user.getId());
    orderDao.insertMOrder(mOrder);
    return orderInfo;
  }
  
}
