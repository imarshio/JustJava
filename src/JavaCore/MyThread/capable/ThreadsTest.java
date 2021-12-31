package JavaCore.MyThread.capable;
/**
* @arithmetics ：
* @author ： masuo
* @time ：2021年5月12日 上午11:17:20
* @类说明 :
*/
public class ThreadsTest {

	
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();
		
		RunnableThread rThread  =new RunnableThread();
		Thread rthread = new Thread(rThread);
		rthread.start();
		
		CallableThread cThread = new CallableThread();
		try {
			cThread.call();
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
	}
}
