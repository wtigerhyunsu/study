package day01;

import java.util.Scanner;

class Customer implements Cus {
   String name;

   public Customer(String name) {
      this.name = name;
   
   }
   
   //register 
   Thread rthread=new Thread() {   
      @Override
      public void run() {
         
         for(int i=0; i<10;i++) {
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("register"+name +i);
         }
      };
      
   };
   //login 
   Thread inthread=new Thread() {
      
      
      @Override
      public void run() {
         
         for(int i=0;i<10;i++) {
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
      
         System.out.println("login"+" " +i);
         }
      };
      
   };
   //logout
   Thread outthread=new Thread() {
      
      @Override
      public void run() {
         for(int i=0;i<10;i++) {
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("logout  "+ i);
         }

         
      };
      
   };

   @Override
   public void register() {
      rthread.start();
      
   }
   @Override
   public void login() {
      inthread.start();
      
   }
   @Override
   public void logout() {
      outthread.start();
      
   }

   
}

public class T6 {

   public static void main(String[] args) {
      
      Cus c= null;
      c = new Customer("KIM");
//      c.login();
//   

      Scanner s=new Scanner(System.in);
      
      while(true) {
      System.out.println("input sth");
      
      
      String cmd=s.next();
      
      if (cmd.equals("R")) {
         c.register();
      }
      
      else if (cmd.equals("L")) {
         c.login();
      }
      
      else if (cmd.equals("O")) {
         c.logout();
      }
      
      else if(cmd.equals("Q")){
         System.out.println("The END--------------");
         break;
         
      }
      
      }
      s.close();
      
   
}


}




