package com.example.tabserver;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import tcpip2.Msg;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView name1, name2, name3, name4,
             data1, data2, data3, data4;

    HashMap<String, ObjectOutputStream> maps = new HashMap<String, ObjectOutputStream>();
    HashMap<String, String> ids = new HashMap<String, String>();

    ArrayAdapter<String> adapter;
    int personIndex = 0;
    ServerSocket serverSocket;
    boolean aflag = true;
    int port = 8888;

    Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeUi();



    }

    class serverReady extends Thread{

        public serverReady(){
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(aflag) {
                Socket socket = null;

                Log.d("-----","Server Ready..");
                try {
                    socket = serverSocket.accept();
                    new Receiver(socket).start();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setList();//왼쪽에 보여지는 부분
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<String> getIds() {
        Collection<String>
                id = ids.values();
        Iterator<String> it = id.iterator();
        ArrayList<String> list = new ArrayList<String>();
        while(it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

    public void sendIp() {
        Set<String>
                keys = maps.keySet();
        Iterator<String>
                its = keys.iterator();
        ArrayList<String> list = new ArrayList<String>();
        while(its.hasNext()) {
            list.add(its.next());
        }
    }

    public void setList(){
        adapter =
                new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        getIds()
                );
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ObjectOutputStream tid = maps.get(ids.get(id));
                Log.d("---","oos 는 "+tid + " " + ids.get(id) );
            }
        });
    }

    class Receiver extends Thread{
        int receiveIndex ;
        InputStream is;
        ObjectInputStream ois;

        OutputStream os;
        ObjectOutputStream oos;

        Socket socket;
        public Receiver(Socket socket) throws IOException {
            receiveIndex = personIndex++;
            this.socket = socket;
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            maps.put(socket.getInetAddress().toString(),
                    oos);
            try {
                Msg msg = (Msg) ois.readObject();

                ids.put(socket.getInetAddress().toString(),
                        msg.getId());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(ois != null) {
                Msg msg = null;
                try {
                    msg = (Msg) ois.readObject();
                    final Msg finalMsg = msg;
                    Log.d("---", "index is" + receiveIndex);
                    if(receiveIndex == 0){
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                data1.setText(finalMsg.getTxt());
                                name1.setText(finalMsg.getId());
                            }
                        });
                    }else if(receiveIndex == 1){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                data2.setText(finalMsg.getTxt());
                                name2.setText(finalMsg.getId());
                            }
                        });
                    }else if(receiveIndex == 2){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                data3.setText(finalMsg.getTxt());
                                name3.setText(finalMsg.getId());
                            }
                        });
                    }else if(receiveIndex == 3){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                data3.setText(finalMsg.getTxt());
                                name3.setText(finalMsg.getId());
                            }
                        });
                    }
                    Log.d("---",""+msg);


                    Log.d("---",""+msg.getId()+msg.getTxt());
                    System.out.println(
                            msg.getId()+":"+msg.getTxt());
                    if(msg.getTxt().equals("q")) {
                        System.out.println(
                                ids.get(socket.getInetAddress().toString())+":Exit ..");

                        maps.remove(
                                socket.getInetAddress().toString()
                        );

                        ids.remove(socket.getInetAddress().toString()
                        );
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setList();
                            }
                        });
                        break;
                    }
                    sendMsg(msg);
                } catch (Exception e) {
                    Log.d("---","errer" + e);

                    maps.remove(
                            socket.getInetAddress().toString()
                    );

                    ids.remove(socket.getInetAddress().toString()
                    );
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setList();
                        }
                    });
                    break;
                }
            } // end while
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
        }

    }

    class Sender extends Thread{
        Msg msg;
        public Sender(Msg msg) {
            this.msg = msg;
        }
        @Override
        public void run() {

            Collection<ObjectOutputStream>
                    cols = maps.values();
            Iterator<ObjectOutputStream>
                    its = cols.iterator();
            while(its.hasNext()) {
                try {
                    its.next().writeObject(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    class Sender2 extends Thread{
        Msg msg;
        public Sender2(Msg msg) {
            this.msg = msg;
        }
        @Override
        public void run() {
            String tid = msg.getTid();
            try {
                Collection<String>
                        col = ids.keySet();
                Iterator<String> it = col.iterator();
                String sip = "";
                while(it.hasNext()) {
                    String key = it.next();
                    if(ids.get(key).equals(tid)) {
                        sip = key;
                    }
                }
                System.out.println(sip);
                maps.get(sip).writeObject(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void sendMsg(Msg msg) {
        String tid = msg.getTid();

        if(tid == null || tid.equals("")) {
            Sender sender =
                    new Sender(msg);
            sender.start();
        }else {
            Sender2 sender2 =
                    new Sender2(msg);
            sender2.start();
        }

    } // end sendMsg




    private void makeUi() {
        listView = findViewById(R.id.listView);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);
        data1 = findViewById(R.id.data1);
        data2 = findViewById(R.id.data2);
        data3 = findViewById(R.id.data3);
        data4 = findViewById(R.id.data4);
    }

    public void ckbt(View v){
        Msg msg = null;
        if(v.getId() == R.id.button){
            new serverReady().start();

            msg = new Msg("server","1",null);
        }else if(v.getId() == R.id.button2){
            msg = new Msg("server","0",null);
        }
        sendMsg(msg);
    }
}
