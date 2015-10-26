package test.redis;

import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class RevThread extends Thread {
	String host = "10.10.1.170";
	int port = 6379;
	int timeout = 10000;
 @Override
public void run() {
	 JedisPool pool = new JedisPool(new JedisPoolConfig(), host, port, timeout);

		Jedis jedis = pool.getResource();
		JedisPubSub jedisPubSub = new JedisPubSub() {
			@Override
			public void onUnsubscribe(String channel, int number) {
				System.out.println("channel: " + channel);
				System.out.println("number :" + number);
			}

			@Override
			public void onSubscribe(String channel, int number) {
				System.out.println("channel: " + channel);
				System.out.println("number :" + number);
			}

			@Override
			public void onPUnsubscribe(String arg0, int arg1) {
			}

			@Override
			public void onPSubscribe(String arg0, int arg1) {
			}

			@Override
			public void onPMessage(String arg0, String arg1, String arg2) {
			}

			int j = 0;

			@Override
			public void onMessage(String channel, String msg) {
				try {
					j++;
					TimeUnit.MICROSECONDS.sleep(100);
			
					if(j%1000==0) {
						System.out.println("recieve= " + j);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Get error!" + e.getMessage());
				}
			}
		};
		jedis.subscribe(jedisPubSub, new String[] { "channel1", "channel2" });
		pool.returnResource(jedis);
}
}
