package com.zs.plus.domain.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderInfo {
  
  private Long id;
  private Long user_id;
  private Long goods_id;
  private Long delivery_addr_id;
  private String goods_name;
  private Integer goods_count;
  private Double goods_price;
  private Integer order_channel;
  private Integer status;
  private Date create_date;
  private Date pay_date;
  
}
