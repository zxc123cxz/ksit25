package com.kaishengit.redis;


import com.google.gson.Gson;
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
@ContextConfiguration(locations ="classpath:spring-data-redis-cluster.xml")
public class SpringDataCluster {

        private RedisTemplate redisTemplate;

        @Autowired
        public void setRedisTemplate(RedisTemplate redisTemplate) {
            this.redisTemplate = redisTemplate;
            this.redisTemplate.setKeySerializer(new StringRedisSerializer());
           /* this.redisTemplate.setValueSerializer(new StringRedisSerializer());*/
            this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        }

    @Test
        public void find(){
           /* redisTemplate.opsForValue().set("user:1","jack");

            System.out.println(redisTemplate.opsForValue().get("user:1"));*/

            User user = new User(3,"大神","无敌的我又迷路了");

            redisTemplate.opsForValue().set("user:5",user);

            System.out.println(redisTemplate.opsForValue().get("user:5"));

        }


}
