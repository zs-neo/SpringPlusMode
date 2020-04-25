package com.zs.plus.domain.vo;

import com.zs.plus.validator.IsMobile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LoginVO {
  
  @NotNull
  @IsMobile
  private String mobile;
  
  @NotNull
  private String password;
  
}
