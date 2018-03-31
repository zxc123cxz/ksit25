package com.kaishengit.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class User {
    private Integer id;
    private String name;
    private  Double score;
    private List<String> nameList;
    private Set<Integer> numSet;

    private UserDao userDao;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", nameList=" + nameList +
                ", numSet=" + numSet +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }

    private Map<String,User> map;
    private Properties properties;

    public void shows(){
        System.out.println("id -->"+id);
        System.out.println("name -->"+name);
        System.out.println("score -->"+score);
        for(String str : nameList){
            System.out.println("str -->"+str);
        }
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public void setNumSet(Set<Integer> numSet) {
        this.numSet = numSet;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
