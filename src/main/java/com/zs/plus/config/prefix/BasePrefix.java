package com.zs.plus.config.prefix;

public abstract class BasePrefix implements KeyPrefix {
  
  private int expireSeconds;
  private String prefix;
  
  public BasePrefix(String prefix) {
    this(0, prefix);
  }
  
  public BasePrefix(int expireSeconds, String prefix) {
    this.expireSeconds = expireSeconds;
    this.prefix = prefix;
  }
  
  @Override
  public int expireSeconds() {
    return expireSeconds;
  }
  
  @Override
  public String getPrefix() {
    String className = getClass().getName();
    return className + ":" + prefix;
  }
  
}
