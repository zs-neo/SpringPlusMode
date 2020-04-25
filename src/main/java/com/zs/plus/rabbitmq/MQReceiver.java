package com.zs.plus.rabbitmq;

import com.zs.plus.config.redis.RedisService;
import com.zs.plus.domain.po.MOrder;
import com.zs.plus.domain.po.MUser;
import com.zs.plus.domain.vo.GoodsVO;
import com.zs.plus.service.GoodsService;
import com.zs.plus.service.MService;
import com.zs.plus.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {
  
  private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
  
  @Autowired
  public RedisService redisService;
  
  @Autowired
  public GoodsService goodsService;
  
  @Autowired
  public OrderService orderService;
  
  @Autowired
  public MService mService;
  
  @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
  public void receive(String message) {
    log.info("Recieve message " + message);
    MMessage mMessage = redisService.stringToBean(message, MMessage.class);
    MUser mUser = mMessage.getMUser();
    long goodsId = mMessage.getGoods_id();
    
    GoodsVO good = goodsService.getGoodsVoByGoodsId(goodsId);
    int stock = good.getStock_Count();
    if (stock <= 0) {
      return;
    }
    //判断是否已经秒杀到了
    MOrder order = orderService.getMOrderById(mUser.getId(), goodsId);
    if (order != null) {
      return;
    }
    //减库存 下订单 写入秒杀订单
    mService.miaosha(mUser, good);
  }
  
  @RabbitListener(queues = MQConfig.QUEUE)
  public void receiveMsg(String message) {
    log.info("Recieve message " + message);
  }
  
  @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
  public void receiveTopic1(String message) {
    log.info("topic queue1 receive message " + message);
  }
  
  @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
  public void receiveTopic2(String message) {
    log.info("topic queue2 receive message " + message);
  }
  
  @RabbitListener(queues = MQConfig.HEADER_QUEUE)
  public void receiveHeaderQueue(byte[] message) {
    log.info("header queue message:" + new String(message));
  }
  
}
