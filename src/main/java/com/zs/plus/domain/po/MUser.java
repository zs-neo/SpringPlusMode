package com.zs.plus.domain.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MUser {
  
  private long id;
  private String nickname;
  private String password;
  private String salt;
  private String head;
  private LocalDateTime registerDate;
  private LocalDateTime lastLoginDate;
  private Integer loginCount;
  
}
