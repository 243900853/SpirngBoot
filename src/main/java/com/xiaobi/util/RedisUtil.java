package com.xiaobi.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	//服务器地址
	private static String host = "127.0.0.1";
	//服务器密码
	private static String password = "xb123";
	//服务器开放使用端口
	private static int port = 6379;
	//连接超时的时间
	private static int timeout = 10000;
	//连接实例的最大连接数
	private static int MAX_TOTAL = 1024;
	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;
	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
	private static int MAX_WAIT_MILLIS = 10000;
	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;
	
	private static JedisPool jedisPool = null;
	
	/*
		调用RedisUtil第一步就会自动初始化Redis连接池
	*/
	static{
		try {
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(MAX_TOTAL);
			poolConfig.setMaxIdle(MAX_IDLE);
			poolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
			poolConfig.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*	
		获取Jedis实例
	*/
	public synchronized static Jedis getJedis(){
		
		try {
			if(jedisPool != null){
				Jedis jedis = jedisPool.getResource();
				return jedis;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/*
		释放资源
	*/
	public static void returnResource(final Jedis jedis){
		if(jedis != null){
			jedisPool.returnResource(jedis);
		}
		
	}
	
}
