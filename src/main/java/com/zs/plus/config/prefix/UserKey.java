package com.zs.plus.config.prefix;

public class UserKey extends BasePrefix {
  
  public static UserKey getById = new UserKey("id");
  public static UserKey getByName = new UserKey("name");
  
  public UserKey(String prefix) {
    super(prefix);
  }
  
}
