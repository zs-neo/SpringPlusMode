package com.zs.plus.domain.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MOrder {
  
  private Long id;
  private Long user_id;
  private Long order_id;
  private Long goods_id;
  
}
