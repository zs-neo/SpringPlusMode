package com.zs.plus.service;

import com.zs.plus.config.prefix.MUserKey;
import com.zs.plus.dao.MUserDao;
import com.zs.plus.domain.CodeMsg;
import com.zs.plus.domain.po.MUser;
import com.zs.plus.domain.vo.LoginVO;
import com.zs.plus.exception.GlobalException;
import com.zs.plus.util.MD5Util;
import com.zs.plus.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MUserService {
  
  public static final String COOKIE_NAME_TOKEN = "token";
  
  private static Logger log = LoggerFactory.getLogger(MUserService.class);
  
  @Autowired
  private MUserDao mUserDao;
  
  @Autowired
  private RedisService redisService;
  
  public MUser getById(long id) {
    return mUserDao.getById(id);
  }
  
  public MUser getByToken(HttpServletResponse response, String token) {
    if (StringUtils.isEmpty(token)) {
      return null;
    }
    MUser mUser = redisService.get(MUserKey.token, token, MUser.class);
    if (mUser != null) {
      addCookie(response, token, mUser);
    }
    return mUser;
  }
  
  public boolean login(HttpServletResponse response, LoginVO loginVO) {
    log.info(loginVO.toString());
    if (loginVO == null) {
      throw new GlobalException(CodeMsg.SERVER_ERROR);
    }
    String mobile = loginVO.getMobile();
    String formPass = loginVO.getPassword();
    MUser mUser = mUserDao.getById(Long.parseLong(mobile));
    if (mUser == null) {
      throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
    }
    String dbPass = mUser.getPassword();
    String salt = mUser.getSalt();
    String calcPass = MD5Util.fromPassToDBPass(formPass, salt);
    if (calcPass.equals(dbPass)) {
      throw new GlobalException(CodeMsg.PASSWORD_ERROR);
    }
    String token = UUIDUtil.uuid();
    addCookie(response, token, mUser);
    return true;
  }
  
  private void addCookie(HttpServletResponse response, String token, MUser mUser) {
    redisService.set(MUserKey.token, token, mUser);
    Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
    cookie.setMaxAge(MUserKey.TOKEN_EXPIRE);
    cookie.setPath("/");
    response.addCookie(cookie);
  }
}
