package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * @author ygh
 *
 */
@Service
public class RedisService {

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	/**
	 * Get value from redis,it will return null if the value don't exsit
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(final String key) {
		return this.execute(new Function<String, ShardedJedis>() {

			@Override
			public String callback(ShardedJedis e) {
				return e.get(key);
			}
		});
	}

	/**
	 * Set key and value into redis cache
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(final String key, final String value) {
		return this.execute(new Function<String, ShardedJedis>() {

			@Override
			public String callback(ShardedJedis e) {
				return e.set(key, value);
			}

		});
	}

	/**
	 * Delete value by key in redis cache
	 * 
	 * @param key
	 * @return
	 */
	public Long del(final String key) {
		return this.execute(new Function<Long, ShardedJedis>() {

			@Override
			public Long callback(ShardedJedis e) {
				return e.del(key);
			}
		});
	}

	/**
	 * Setting life time for key in redis
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(final String key, final Integer seconds) {
		return this.execute(new Function<Long, ShardedJedis>() {

			@Override
			public Long callback(ShardedJedis e) {
				return e.expire(key, seconds);
			}
		});
	}

	private <T> T execute(Function<T, ShardedJedis> fun) {
		ShardedJedis jedis = null;
		T value = null;
		try {
			jedis = shardedJedisPool.getResource();
			value = fun.callback(jedis);
		} catch (Exception e) {

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

}
