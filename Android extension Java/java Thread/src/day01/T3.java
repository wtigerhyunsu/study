package day01;

import java.awt.Frame;
import java.awt.List;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


class FrameApp{
	
	Frame f;
	List list1,list2;
	public FrameApp() {
		f =new Frame();
		list1 = new List();
		list2 = new List();
		
		f.add(list1,"East");
		f.add(list2,"West");
		f.setSize(300,300);
		f.setVisible(true);
		f.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				f.setVisible(false);
				System.exit(0);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		t1.start();
		t2.start();
		
	}
	Thread t1 =new Thread() {

		@Override
		public void run() {
			setData1();
		}
		
		
	};
	Thread t2 =new Thread() {
		@Override
		public void run() {
			setData2();
		}
	};
	
	
	public void setData1() {
		for(int i=1;i<=50;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add("Item:"+i);
			
		}
	}
	
	
	public void setData2() {
		for(int i=1;i<=50;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add("Item:"+i);
			
		}
	}
	
	
}


public class T3 {
	 
	public static void main(String[] args) {
		FrameApp f =new FrameApp();
		f.setData1();
		f.setData2();
	
		


		
		
	}

}
