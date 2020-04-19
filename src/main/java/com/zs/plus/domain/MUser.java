package com.zs.plus.domain;

import java.time.LocalDateTime;

public class MUser {
  
  private long id;
  private String nickname;
  private String password;
  private String salt;
  private String head;
  private LocalDateTime registerDate;
  private LocalDateTime lastLoginDate;
  private Integer loginCount;
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getNickname() {
    return nickname;
  }
  
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getSalt() {
    return salt;
  }
  
  public void setSalt(String salt) {
    this.salt = salt;
  }
  
  public String getHead() {
    return head;
  }
  
  public void setHead(String head) {
    this.head = head;
  }
  
  public LocalDateTime getRegisterDate() {
    return registerDate;
  }
  
  public void setRegisterDate(LocalDateTime registerDate) {
    this.registerDate = registerDate;
  }
  
  public LocalDateTime getLastLoginDate() {
    return lastLoginDate;
  }
  
  public void setLastLoginDate(LocalDateTime lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }
  
  public Integer getLoginCount() {
    return loginCount;
  }
  
  public void setLoginCount(Integer loginCount) {
    this.loginCount = loginCount;
  }
  
  @Override
  public String toString() {
    return "MUser{" +
      "id=" + id +
      ", nickname='" + nickname + '\'' +
      ", password='" + password + '\'' +
      ", salt='" + salt + '\'' +
      ", head='" + head + '\'' +
      ", registerDate=" + registerDate +
      ", lastLoginDate=" + lastLoginDate +
      ", loginCount=" + loginCount +
      '}';
  }
}
