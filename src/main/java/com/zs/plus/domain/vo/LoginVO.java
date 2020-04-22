package com.zs.plus.domain.vo;

import com.zs.plus.validator.IsMobile;

import javax.validation.constraints.NotNull;

public class LoginVO {
  
  @NotNull
  @IsMobile
  private String mobile;
  
  @NotNull
  private String password;
  
  public String getMobile() {
    return mobile;
  }
  
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String toString() {
    return "LoginVO{" +
      "mobile='" + mobile + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
