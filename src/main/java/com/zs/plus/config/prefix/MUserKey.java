package com.zs.plus.config.prefix;

public class MUserKey extends BasePrefix {
  
  public static final int TOKEN_EXPIRE = 3600 * 24 * 2;
  public static MUserKey token = new MUserKey(TOKEN_EXPIRE, "tk");
  
  public MUserKey(String prefix) {
    super(prefix);
  }
  
  public MUserKey(int expireSeconds, String prefix) {
    super(expireSeconds, prefix);
  }
}
