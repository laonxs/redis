package cn.laonxs;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis
 *
 * @author ade
 * @version 1.0，2020-04-13 22:11:22
 */
public class TestRedis {

    @Test
    public void test01() {
        Jedis jedis = new Jedis("192.168.92.128", 6379);

        String set = jedis.set("name", "laonxs");
        System.out.println(set);

        String name = jedis.get("name");
        System.out.println(name);

        jedis.close();
    }

    @Test
    public void test02() {
        // 获取连接池配置对象，并进行配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);     // 配置最大连接数
        poolConfig.setMaxIdle(10);      // 配置最大空闲连接数

        // 获取连接池对象
        JedisPool pool = new JedisPool("192.168.92.128", 6379);

        // 获取核心对象
        Jedis jedis = pool.getResource();

        // 操作
        String set = jedis.set("name", "laonxs");
        System.out.println(set);        // OK

        String name = jedis.get("name");
        System.out.println(name);       // laonxs

        // 关闭资源
        jedis.close();
        pool.close();
    }

}
