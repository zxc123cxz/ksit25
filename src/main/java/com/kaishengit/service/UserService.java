package com.kaishengit.service;

import com.kaishengit.entity.UserDao;

import java.util.Set;

public class UserService {
    private  UserDao userDao;
    /*public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    */
    private Set numSet;

    public UserService(UserDao userDao){
      this.userDao = userDao;
   }

    public void save(){
       userDao.save();
        System.out.println("成功");
    }
}
