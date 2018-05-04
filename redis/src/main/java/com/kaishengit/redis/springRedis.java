package com.kaishengit.redis;


import com.google.gson.Gson;
import com.kaishengit.redis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:spring-redis.xml" )
public class springRedis {

    @Autowired
    private JedisPool jedisPool;


    @Test
    public void find(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("user:1:name","文龙");

        System.out.println(jedis.get("user:1:name"));

    }

    /*
     *  redis传入一个对象
     *  用json
     * @date 2018/5/3
     * @param
     * @return
     */
    @Test
    public void  save(){
        Jedis jedis = jedisPool.getResource();

        User user = new User(1,"文龙","周口");
        String json = new Gson().toJson(user);

        jedis.set("user:1",json);

        System.out.println(jedis.get("user:1"));
        jedis.close();
    }



}
