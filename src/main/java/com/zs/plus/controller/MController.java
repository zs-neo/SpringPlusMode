package com.zs.plus.controller;

import com.zs.plus.config.prefix.GoodsKey;
import com.zs.plus.config.redis.RedisService;
import com.zs.plus.domain.CodeMsg;
import com.zs.plus.domain.Result;
import com.zs.plus.domain.po.MOrder;
import com.zs.plus.domain.po.MUser;
import com.zs.plus.domain.vo.GoodsVO;
import com.zs.plus.rabbitmq.MMessage;
import com.zs.plus.rabbitmq.MQSender;
import com.zs.plus.service.GoodsService;
import com.zs.plus.service.MService;
import com.zs.plus.service.MUserService;
import com.zs.plus.service.OrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app")
public class MController implements InitializingBean {
  
  @Autowired
  MUserService mUserService;
  
  @Autowired
  RedisService redisService;
  
  @Autowired
  GoodsService goodsService;
  
  @Autowired
  OrderService orderService;
  
  @Autowired
  MService mService;
  
  @Autowired
  MQSender mqSender;
  
  @Override
  public void afterPropertiesSet() throws Exception {
    List<GoodsVO> goodsList = goodsService.listGoodsVO();
    if (goodsList == null) {
      return;
    }
    for (GoodsVO good : goodsList) {
      redisService.set(GoodsKey.getMGoodsStock, "" + good.getId(), good.getStock_Count());
    }
  }
  
  @RequestMapping("/miaosha")
  public Result list(MUser mUser, long goodsId) {
    if (mUser == null) {
      return new Result(CodeMsg.SERVER_ERROR);
    }
    //预减库存
    long stock = redisService.decr(GoodsKey.getMGoodsStock, "" + goodsId);
    if (stock < 0) {
      return Result.error(CodeMsg.MIAO_SHA_OVER);
    }
    //判断是否已经秒杀到了
    MOrder order = orderService.getMOrderById(mUser.getId(), goodsId);
    if (order != null) {
      return Result.error(CodeMsg.REPEATE_MIAOSHA);
    }
    //入队
    MMessage mMessage = new MMessage();
    mMessage.setMUser(mUser);
    mMessage.setGoods_id(goodsId);
    mqSender.sendMMessage(mMessage);
    return Result.success(0);
  }
  
}
