import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class HelloWoord {


    @Test
    public void Hello(){

        Jedis jedis  = new Jedis("172.16.1.11",6379);
        String pong = jedis.ping();
        System.out.print(pong);
        jedis.close();
    }

    /*
     * redis list是以双向链表是实现的
     * 是有序插入的
     * 主要表现在查询时 两边快中间慢
     * @date 2018/5/2
     * @param
     * @return
     */
    @Test
    public void list(){
        Jedis jedis = new Jedis("172.16.1.11",6379);
        jedis.lpush("user:1:name","zhangsan");
        jedis.lpush("user:1:name","lisi");
        jedis.lpush("user:1:name","tom");
       /* String stringList =  jedis.lpop("user:1:name");*/
       List<String> stringList =  jedis.lrange("user:1:name",0,2);

       System.out.println(stringList);

        jedis.close();
    }


    /*
     * set 是string的无序集合
     * 集合是通过哈希表实现的
     * 不允许数据重复
     * @date 2018/5/2
     * @param
     * @return
     */
    @Test
    public void set(){

        Jedis jedis = new Jedis("172.16.1.11",6379);
        jedis.sadd("user:1","jack");

    }


    @Test
    public void zset(){
    /*
    zset 和 set 一样也是string类型元素的集合,且不允许重复的成员
    不同的是每个元素都会关联一个double类型的分数。
    redis正是通过分数来为集合中的成员进行从小到大的排序。zset的成员是唯一的,但分数(score)却可以重复。
    和list相比都是有序的，可以获取某个范围内的数据

    list 通过链表实现，获取两端数据速度快，但是随着数据量增加，获取中间的数据时较慢
    zset 通过散列表实现和跳跃表(skip list)实现，即使读取的是中间的部分，也可以速度很快
    zset 比list 更耗内存
    list 不能简单的调整某个元素的位置，但是 zset 可以(调整元素的分数)
     */

    }
}
