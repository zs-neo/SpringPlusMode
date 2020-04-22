package com.zs.plus.controller;

import com.zs.plus.domain.MUser;
import com.zs.plus.service.MUserService;
import com.zs.plus.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

  @Autowired
  MUserService mUserService;

  @Autowired
  RedisService redisService;
  
  @RequestMapping("tolist")
  public String list(Model model, MUser mUser){
    model.addAttribute("user",mUser);
    return "";
  }
  
}
