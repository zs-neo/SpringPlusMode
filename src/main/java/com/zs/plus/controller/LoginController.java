package com.zs.plus.controller;

import com.zs.plus.domain.CodeMsg;
import com.zs.plus.domain.MUser;
import com.zs.plus.domain.Result;
import com.zs.plus.domain.User;
import com.zs.plus.domain.vo.LoginVO;
import com.zs.plus.service.MUserService;
import com.zs.plus.service.RedisService;
import com.zs.plus.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/app/user")
public class LoginController {
  
  private static Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
  
  @Autowired
  private MUserService mUserService;
  
  @RequestMapping("/login")
  @ResponseBody
  public Result doLogin(HttpServletResponse response,@Valid LoginVO loginVO) {
    logger.info(loginVO.toString());
    if(mUserService.login(response,loginVO)){
      return new Result<>(CodeMsg.SUCCESS);
    }else{
      return new Result<>(CodeMsg.PASSWORD_ERROR);
    }
  }
  
}
