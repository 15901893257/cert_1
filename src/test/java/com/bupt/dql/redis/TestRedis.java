package com.bupt.dql.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: mai
 * @date: 2019/8/12
 */
public class TestRedis {

    private String host = "10.103.246.68";
    private String host1 = "10.103.250.146";
    private Jedis jedis;

    @Before
    public void before(){
        jedis = new Jedis(host1);
    }

    /**
     * 输出所有keys
     */
    @Test
    public void getKeys(){
        Set<String> keys = jedis.keys("*");
        for(String key : keys){
            System.out.println(key);
        }
        System.out.println(keys.size());
    }

    @Test
    public void getList(){
        Map<String,String> map = jedis.hgetAll("C/slact/redis/redis-new");
        Set<String> keySet = map.keySet();
        System.out.println("开始");
        for(String key : keySet){
            System.out.println(key);
            System.out.println(map.get(key));
        }

    }


}
