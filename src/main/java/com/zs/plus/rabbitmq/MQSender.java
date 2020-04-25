package com.zs.plus.rabbitmq;

import com.zs.plus.config.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {
  
  private static Logger log = LoggerFactory.getLogger(MQSender.class);
  
  @Autowired
  private RedisService redisService;
  
  @Autowired
  private AmqpTemplate amqpTemplate;
  
  public void sendMMessage(MMessage mMessage) {
    String msg = redisService.beanToString(mMessage);
    log.info("Send msg " + msg);
    amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
  }
  
  public void send(Object message) {
    String msg = redisService.beanToString(message);
    log.info("Send msg " + msg);
    amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
  }
  
  public void sendTopic(Object message) {
    String msg = redisService.beanToString(message);
    log.info("Send topic msg " + msg);
    amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg + "-1");
    amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg + "-1");
  }
  
  public void sendFanout(Object message) {
    String msg = redisService.beanToString(message);
    log.info("Send topic msg " + msg);
    amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
  }
  
  public void sendHeader(Object message) {
    String msg = redisService.beanToString(message);
    log.info("Send header msg " + msg);
    MessageProperties properties = new MessageProperties();
    properties.setHeader("header1", "value1");
    properties.setHeader("header2", "value2");
    Message obj = new Message(msg.getBytes(), properties);
    amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
  }
  
}
