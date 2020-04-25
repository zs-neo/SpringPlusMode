package com.zs.plus.rabbitmq;

import com.zs.plus.domain.po.MUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MMessage {
  
  private MUser mUser;
  private long goods_id;
  
}
