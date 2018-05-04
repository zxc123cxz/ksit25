package com.kaishengit.redis;


import com.kaishengit.redis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-date-redis.xml")
public class SpringDateRedis {

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        //this.redisTemplate.setValueSerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }
    @Test
    public void find(){
   /*     //操作字符串
        redisTemplate.opsForValue().set("name","文龙");
        System.out.println(redisTemplate.opsForValue().get("name"));*/
        User user = new User(12,"文龙","天才");

        redisTemplate.opsForValue().set("user:12",user);

        System.out.println(redisTemplate.opsForValue().get("user:12"));
    }

}
