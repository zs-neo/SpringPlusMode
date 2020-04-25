package com.zs.plus.domain.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Goods {
  
  private Long id;
  private String goods_name;
  private String goods_title;
  private String goods_img;
  private String goods_detail;
  private Double goods_price;
  private Integer goods_stock;
  
}
