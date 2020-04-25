package com.zs.plus.domain.vo;

import com.zs.plus.domain.po.Goods;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class GoodsVO extends Goods {
  
  private Double m_Price;
  private Integer stock_Count;
  private Date start_Date;
  private Date end_Date;
  
}
