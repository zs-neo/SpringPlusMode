package com.zs.plus.service;

import com.zs.plus.dao.MUserDao;
import com.zs.plus.domain.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MUserService {

  public static final String COOKIE_NAME_TOKEN = "token";
  
  @Autowired
  private MUserDao mUserDao;
  
  public MUser getById(long id){
    return mUserDao.getById(id);
  }
  
  

}
