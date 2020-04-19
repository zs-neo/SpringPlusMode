package com.zs.plus.controller;

import com.zs.plus.config.prefix.UserKey;
import com.zs.plus.domain.CodeMsg;
import com.zs.plus.domain.Result;
import com.zs.plus.domain.User;
import com.zs.plus.service.RedisService;
import com.zs.plus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app/user")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private RedisService redisService;
  
  @RequestMapping("/error")
  @ResponseBody
  public Result<String> error() {
    return new Result<String>(CodeMsg.SERVER_ERROR);
  }
  
  @RequestMapping("/get")
  @ResponseBody
  public Result<User> get(@RequestParam("id") int id) {
    return Result.success(userService.getById(id));
  }
  
  @RequestMapping("/put")
  @ResponseBody
  public Result<User> put(User user) {
    return Result.success(userService.insert(user));
  }
  
  @RequestMapping("/redis/get")
  @ResponseBody
  public Result<User> get(User user) {
    User res = redisService.get(UserKey.getById, "" + user.getId(), User.class);
    return Result.success(res);
  }
  
  @RequestMapping("/redis/set")
  @ResponseBody
  public Result<Boolean> redisSet(User user) {
    boolean res = redisService.set(UserKey.getById, "" + user.getId(), user);
    return Result.success(res);
  }
  
}
