package com.xiaobi.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RefreshRedisTest {
	
	@Test
	public void demo(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		JedisPool jedisPool = new JedisPool(config,"127.0.0.1",6379,1000,"xb123");
		Jedis jedis = jedisPool.getResource();
		jedis.set("ceshi", "1245");
		jedis.del("ceshi");
//		Jedis jedis = new Jedis("127.0.0.1",6379);
		//jedis.set("name", "肖毕");
		//String value = jedis.get("name");
		String name = jedis.get("myKey");
		String ceshi = jedis.get("ceshi");
		System.out.println(name+ceshi);
	}
}
