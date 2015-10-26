/**
 * 订阅消息测试
 * 
 */
package test.redis;

import redis.clients.jedis.Jedis; 
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lij
 * @version <b>1.0</b>
 */
public class JedisPubSubTestApp {

	public static void main(String[] args) {
		final JedisPubSubTestApp jedisPoolTest = new JedisPubSubTestApp();
		/**
		 * �߳��������������
		 */
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("test");
				while(true) {
					jedisPoolTest.send();
				}
			};
		}.start();
		System.out.println("test!!!");
		
		//���������߳�
		new RevThread().start();
	}

	String host = "10.10.1.170";
	int port = 6379;
	int timeout = 10000;


	JedisPool pool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
	Jedis jedis = pool.getResource();
	int i=0;
	
	/**
	 * ������Ϣ
	 */
	private void send() {
		i++;
		jedis.publish("channel1", " aaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbddddddddddddddddddddccccccccccccc"
				+ "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		if(i%10000==0) {
			System.out.println("##############send= " + i);
		}
	}
}
