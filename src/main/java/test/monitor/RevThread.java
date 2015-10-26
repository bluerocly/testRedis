package test.monitor;

import java.util.HashMap;
import java.util.Map;

/**
  * 模拟接收线程异常
 * @author lij
 *
 */
public class RevThread extends Thread {
 @Override
	public void run() {
		 Map map = new HashMap();
		 int i=0;
		while(true) {
			String s = new String("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS" + i);
			map.put(""+i++, s);
			if(i%100000==0) {
				System.out.println("SSS" + i);
			}
		}
	}
}
