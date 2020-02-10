package day01;




import java.util.Scanner;
import java.util.jar.Attributes.Name;

class Register1 extends Thread{
	String name;
	
	public Register1(String name) {
		this.name =name;
	}
	
	@Override
	public void run() {
		for(int i=1;i<=10;i++) {
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"...");
		}
			System.out.println(name+"Registered...");
	}
	
}
//¼­¹ö
public class Ex {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true) {
			
			System.out.println("Ready..");
			String name =s.nextLine();
			if(name.equals("q")) {
				break;
			}
			Thread t = new Register1(name);
			t.start();
			
		}s.close();
		}

		

	}
