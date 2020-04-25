package com.zs.plus.domain.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MGoods {
  
  private Long id;
  private Long goods_id;
  private Integer stock_count;
  private Date start_date;
  private Date end_date;
  
}
