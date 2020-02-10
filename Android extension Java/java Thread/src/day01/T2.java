package day01;


class MyT implements Runnable{
	String name ;
	boolean flag;
	
	public MyT() {}
	public MyT(String name) {
		this.name =name;
		flag = true;
	}
	@Override
	public void run() {
		while(true) {
			if(flag == false) {
				System.out.println("Disconnevted..........");
				break;
			}
			
			System.out.println(name);
		}
		System.out.println("End@@@@@@@@@@@@@@@@@@@@@@@"+name);
	}
	
	public void setFlag(boolean b) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	      this.flag= flag;
		// TODO Auto-generated method stub
		
	}
	
}

public class T2 {

	public static void main(String[] args) throws InterruptedException {
		MyT r =new MyT("R1");
		Thread t1 = new Thread(r);
//종료방법
		t1.setDaemon(true);
		t1.start();
		Thread.sleep(3000);
		r.setFlag(false);
		
	//여기서 다시 쓰레드를 실행 하고싶다면 다시 쓰레드를 실행해야된다 쓰레드를 중지시켰다가 다시 실행 할수 없다
	

}
}