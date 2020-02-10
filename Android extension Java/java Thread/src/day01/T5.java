package day01;

import java.util.Scanner;
import java.util.jar.Attributes.Name;

class Register implements Runnable{
	String name;
	boolean flag;
	
	public Register() {
		super();
	}
	
	
	public Register(String name) {
		
		this.name = name;
		flag=true;
	}



	public Register(Scanner s) {
		
			name =s.next();
		System.out.println(name);
		run();
				
	}

	@Override
	public void run() {
		while(flag){
			if(name.equals("xx")) {
				System.out.println("회원 가입");
				break;
			}
		System.out.println("name:"+name);
			
		}
			
		
	}
	
}
//서버
public class T5 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("여기 1");
		Register r =new Register(s);
		System.out.println("여기 2");
		
		Thread t1 = new Thread(r);
	
		t1.start();
	
		}

		

	}


