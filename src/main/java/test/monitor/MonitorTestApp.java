/**
 * 监控接收线程，如果发现异常则重新启动
 */
package test.monitor;

import java.util.concurrent.TimeUnit;


public class MonitorTestApp {
	public static void main(String[] args) {
		 RevThread revThread = new RevThread();
		Glob.THREADNAME = revThread;
		revThread.setDaemon(true);
		revThread.start();
		while(true) {
			System.out.println(revThread.getState());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				test();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void test() throws InterruptedException {
		final RevThread rr = (RevThread) Glob.THREADNAME;
		System.out.println(rr.getState());
		if(Thread.State.TERMINATED.equals(rr.getState())) {
			System.out.println("#######");
			Thread.sleep(10000);
			 RevThread revThread = new RevThread();
				Glob.THREADNAME = revThread;
				revThread.setDaemon(true);
				revThread.start();
		}
	}
	
}
