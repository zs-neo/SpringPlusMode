package com.zs.plus.config.prefix;

public class OrderKey extends BasePrefix {
  
  public OrderKey(String prefix) {
    super(prefix);
  }
  
  public static OrderKey getMOrderByUidGid = new OrderKey("mobug");
}
