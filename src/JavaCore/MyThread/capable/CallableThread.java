package JavaCore.MyThread.capable;

import java.util.concurrent.Callable;

/**
* @arithmetics ：
* @author ： masuo
* @date ：2021年5月14日 下午8:35:03
*  :
*/
public class CallableThread implements Callable<String>{

	@Override
	public String call() throws Exception {
		//  线程业务逻辑
		System.out.println("callable thread");
		return null;
	}

}
