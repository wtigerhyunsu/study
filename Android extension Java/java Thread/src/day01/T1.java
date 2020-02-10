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
			yield();//���� �����尡 �ݺ�������� �ʵ���
			System.out.println(name+":"+i);
					}
			}
	
	
}
//�����尡 ���ư��� �߰��� �����Ҽ����� ����� ��� �� �����ϰ� 
//os�� �����ϱ� ������ 

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
	//�켱������ �����Ҽ��ִ�
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	
		// TODO Auto-generated method stub

	}

}
