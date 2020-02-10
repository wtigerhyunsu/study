package day01;

class  MyTread extends Thread{
	String name ;
	public MyTread() {
		
	}
	public MyTread(String name) {
		this.name =name;
	}
	
	@Override
	public void run() {
		for(int i= 1;i<=500;i++) {
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			yield();//같은 쓰레드가 반복재생되지 않도록
			System.out.println(name+":"+i);
					}
			}
	
	
}
//쓰레드가 돌아가는 중간에 제어할수없다 멈춤과 재생 을 제외하고 
//os가 관장하기 떄문에 

public class T1 {

	public static void main(String[] args) {
	Thread t1 = new MyTread("t1");
	Thread t2 = new MyTread("t2");
	Thread t3 = new MyTread("t3");
	Thread t4 = new MyTread("t4");
	t1.setPriority(1);
	t2.setPriority(1);
	t3.setPriority(1);
	t4.setPriority(10);
	//우선순위를 결정할수있는
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	
		// TODO Auto-generated method stub

	}

}
