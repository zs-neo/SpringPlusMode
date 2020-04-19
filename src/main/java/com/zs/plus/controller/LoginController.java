package com.zs.plus.controller;

import com.zs.plus.config.prefix.UserKey;
import com.zs.plus.domain.Result;
import com.zs.plus.domain.User;
import com.zs.plus.domain.vo.LoginVO;
import com.zs.plus.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
  
  @Autowired
  private RedisService redisService;
  
  @RequestMapping("/redis/get")
  @ResponseBody
  public Result<User> doLogin(LoginVO loginVO) {
    return null;
  }
  
}
