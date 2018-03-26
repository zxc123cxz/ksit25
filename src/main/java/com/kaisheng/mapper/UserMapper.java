package com.kaisheng.mapper;

import com.kaisheng.entity.User;

import java.util.List;

/**
 * UserMapper.xml映射接口
 * @author 刘文龙
 * @date 2018/3/23
 */

public interface UserMapper {
    /**
     *
     * @date 2018/3/23
     * @return int
     */
   /* User findById(int id);*/
    List<User> findAll();
    User findById(int id);
    int findByid(int id);
    int update(User user);

}
