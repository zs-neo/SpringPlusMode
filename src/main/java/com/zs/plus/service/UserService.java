package com.zs.plus.service;

import com.zs.plus.dao.UserDao;
import com.zs.plus.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  
  @Autowired
  private UserDao userDao;
  
  public User getById(int id) {
    return userDao.getById(id);
  }
  
  public User insert(User user) {
    return userDao.insert(user);
  }
  
}
