package day01;

class Calc extends Thread{
	int cnt;
	int sum;
	public Calc(int cnt) {
		this.cnt =cnt;
	}
	
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		sum=0;
		//1부터 cnt 까지의 합을 구함
		//Thread.sleep(200)
		for(int i =1;i<=cnt;i++) {
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			sum +=i;
			System.out.println(sum+"hi");
			
			
		}
		
		
	}
	
}

public class T7 {

	public static void main(String[] args) {
		Calc c =new Calc(100);
		c.start();
		int sum=c.getSum();
		System.out.println(sum+"-------------------------------");
		try {
			c.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sum1=c.getSum();
		
		System.out.println(sum1+"-------------------------------");
	
	}

}
