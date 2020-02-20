## 안드로이드 Socket 통신 

> IoT장비와 통신을 하고 개별적인 움직임? 동작? 을 할수있도록 공부하는 내용이다

1) UI 

> 점점 android의 UI이가 복잡해지면서 그동안 onCreate에 핸들링했던 방식으로 하면 느려지니까 함수를 하나 만들어서 핸들링 한다 

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    makUi();

}

private void makUi() {
    editText =findViewById(R.id.editText);
    editText2 =findViewById(R.id.editText2);
    editText3 =findViewById(R.id.editText3);
    editText4 =findViewById(R.id.editText4);
    button =findViewById(R.id.button);
    button2 =findViewById(R.id.button2);
    textView =findViewById(R.id.textView);

}
```

2)sockrt 만들기

> 버튼을 누르면 입력받은 ip,port값을 받아서 Connet를 시도 한다 이떄  Thread를 사용 해서 실행한다 ip와port 값에 맞는 서버에 연결 된다 

```java

public void ckbt(View v){
        if(v.getId()==R.id.button){
            String ip =editText.getText().toString();
            int port= Integer.parseInt(editText2.getText().toString());
            new ConnetThread(ip,port).start();

class ConnetThread extends Thread {
    String ip;
    int port;
    public ConnetThread() {
    }
    public ConnetThread(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    @Override
    public void run() {
        try {
            socket = new Socket(ip, port);
            Log.d("---","1"+socket);
            textView.setText(ip + "Conected\n" + textView.getText());
            //지속적으로 다시 소캣을 만드는
        } catch (Exception e) {
            while (true) {
                textView.setText(ip + "Retry\n" + textView.getText());
                Log.d("---","2"+socket);
                try {
                    Thread.sleep(1000);
                    socket = new Socket(ip, port);
                    Log.d("---","3"+socket);
                    break;
                } catch (Exception e1) {
                    //e1.printStackTrace();
                }
            }
        }
        try {
            sender = new Sender(socket);
             new Receiver(socket).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
  }
```

3)AsyncTask로 서버에서 입력하는 값 받기 

>뭔가 입력 받는 중에 따로 핸들링 하는 부분이 없기떄문에 Void로 값을 받는다 위에서 connet를 실행하면서 Receiver가 호출 되는데 이때 들어오는 값이 없기 떄문에 ip,port값을 가지고 대기 하고 있다가 ip,port가 맞는 서버에서 msg가들어오는데 거기서 txt만 뿌린다 

```java
class Receiver extends AsyncTask<Void,Void,Void>{

    InputStream is;
    ObjectInputStream ois;

    public Receiver(Socket socket) throws IOException {
        is = socket.getInputStream();
        ois = new ObjectInputStream(is);
    }

    @Override
    protected void onPreExecute()  {
    }

   *** @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(Void... voids) {
        while(ois != null) {
            Msg msg = null;
            Log.d("---","msg"+msg);
            try {
                msg = (Msg) ois.readObject();
                textView.setText(msg.getMsg());
                System.out.println(
                        msg.getId()+":"+msg.getMsg()
                );
            }catch(Exception e) {
                System.out.println("Server Die");
                break;
            }
        }
        try {
            if(ois != null) {
                ois.close();
            }
            if(socket != null) {
                socket.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onCancelled() {
        textView.setText((CharSequence) ois);
    }

}
```

4)접속한 IP 한쪽에 띄우기

> outputStream이 Asysnc 가 되지 않아 

```
package tcpip2;

import java.io.Serializable;
import java.util.ArrayList;

public class Msg implements Serializable{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   String id;
   String msg;
   ArrayList<String> ips;
   String ip;
   public Msg(String system, String server_disconnected, Object o) {
   }
   public Msg(String id, String msg, ArrayList<String> ips, String ip) {
      super();
      this.id = id;
      this.msg = msg;
      this.ips = ips;
      this.ip = ip;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getMsg() {
      return msg;
   }
   public void setMsg(String msg) {
      this.msg = msg;
   }
   public ArrayList<String> getIps() {
      return ips;
   }
   public void setIps(ArrayList<String> ips) {
      this.ips = ips;
   }
   public String getIp() {
      return ip;
   }
   public void setIp(String ip) {
      this.ip = ip;
   }
   public static long getSerialversionuid() {
      return serialVersionUID;
   }
   
   
}
```

```
package com.example.chattest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import tcpip2.Msg;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText2,editText3,editText4;
    TextView textView,textView2;

    Socket socket;
    Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeUi();

    }

    private void makeUi() {
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    class ConnectThread extends Thread{

        String ip;
        int port;

        public ConnectThread(){

        }
        public ConnectThread(String ip, int port){
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run() {
            try {
                socket = new Socket(ip, port);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(ip+"Connected \n"+textView.getText());
                    }
                });

            }catch(Exception e) {
                while(true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(ip+"Retry \n"+textView.getText());
                        }
                    });
                    try {
                        Thread.sleep(1000);
                        socket = new Socket(ip, port);
                        break;
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            try {
                sender = new Sender(socket);
                Receiver receiver = new Receiver(socket);
                receiver.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    class Sender implements Runnable{

        OutputStream os;
        ObjectOutputStream oos;
        Msg msg;

        public Sender(Socket socket) throws IOException {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
        }
        public void setMsg(Msg msg) {
            this.msg = msg;
        }
        @Override
        public void run() {

            if(oos != null) {
                try {
                    oos.writeObject(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    class Receiver extends AsyncTask<Void,Msg,Void>{
        InputStream is;
        ObjectInputStream ois;

        public Receiver(Socket socket) throws IOException {
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (ois != null) {
                Msg msg = null;
                try {
                    msg = (Msg) ois.readObject();
                    publishProgress(msg);
                } catch (Exception e) {
                    msg = new Msg("System","Server disconnected",null);
                    publishProgress(msg);
                    break;
                }
            }
            return  null;
        }

        @Override
        protected void onProgressUpdate(Msg... values) {
            if(values[0].getIps() == null || values[0].getIps().size() == 0){
                String txt = values[0].getId()+":"+values[0].getMsg();
                textView.setText(txt+"\n"+textView.getText());
            }else{
                ArrayList<String> list = values[0].getIps();
                String str = "";
                for(String ip:list){
                    str += ip+"\n";
                }
                textView2.setText(str);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                if (ois != null)
                    ois.close();

                if (socket != null)
                    socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void ckbt(View v) throws IOException {
        if(v.getId() == R.id.button_connect){
            String ip =null;
            int port = 0;
            try {
                ip = editText.getText().toString();
                port = Integer.parseInt(editText2.getText().toString());
                new ConnectThread(ip,port).start();
            }catch(Exception e){
                Toast.makeText(this, "올바른 ip 주소와 포트 번호를 입력하세요", Toast.LENGTH_SHORT).show();
            }

        }else if(v.getId() == R.id.button_send){
            String ip = editText3.getText().toString();
            String txt = editText4.getText().toString();
            Msg msg = null;
            if(ip == null || ip.equals("")){
                msg = new Msg("ydh",txt,null);
            }else{
                msg = new Msg("ydh",txt,ip);
            }
            sender.setMsg(msg);
            new Thread(sender).start();
            editText4.setText("");


        }else if(v.getId()==R.id.button_disconnect){
            Msg msg =null;
            msg = new Msg("ydh","q",null);
            sender.setMsg(msg);
            socket.close();
        }else if(v.getId()==R.id.button_reset){
            editText.setText("");
            editText2.setText("");
            textView.setText("");
            textView2.setText("");

            Msg msg =null;
            msg = new Msg("ydh","q",null);
            sender.setMsg(msg);
            socket.close();
        }
    }
}
```