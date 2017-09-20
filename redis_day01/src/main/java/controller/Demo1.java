package controller;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author yuangg
 *
 * @date 2017年9月18日
 */
public class Demo1 {
	public static JedisPool pool;
	public static void main(String[] args) {
		pool = new JedisPool(new JedisPoolConfig(),"localhost");
		Jedis jedis = pool.getResource();
		jedis.set("foo", "bar");
		String foobar = jedis.get("foo");
		System.out.println(foobar);
		jedis.zadd("sose",10,"car");
//		jedis.zadd"sose", 0,"car");
		Set<String> sose = jedis.zrange("sose", 0, -1);
		System.out.println(sose);
		pool.close();
	}
}
