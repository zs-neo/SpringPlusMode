package com.zs.plus.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MVO {
  
  private long miaoshaStatus;
  private long remainSeconds;
  
  public long getMiaoshaStatus() {
    return miaoshaStatus;
  }
  
  public void setMiaoshaStatus(long miaoshaStatus) {
    this.miaoshaStatus = miaoshaStatus;
  }
  
  public long getRemainSeconds() {
    return remainSeconds;
  }
  
  public void setRemainSeconds(long remainSeconds) {
    this.remainSeconds = remainSeconds;
  }
}
