package com.kaishengit.entity;

import com.kaishengit.service.UserService;

public class UserDao {


    private User user;
    private UserService userService;

    public UserDao(){
        System.out.println("123456");
    }


   /*public UserDao(UserDao userDao){
       this.userDao = userDao;
   }*/


/*

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
*/

    /*public void setUser(User user) {
        this.user = user;
    }*/



    public void init(){
        System.out.println("78910");
    }


    public void save() {
        System.out.println("save --> 123");
    }

   /* public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    public void siel() {

        user.shows();
    }

}
