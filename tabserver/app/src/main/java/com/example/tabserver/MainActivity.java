package com.example.tabserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import tcpip2.Msg;
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

public class MainActivity extends AppCompatActivity {
    TextView c1Id, c2Id, c3Id, c4Id;
    TextView c1Data, c2Data, c3Data, c4Data;
    ListView listView;
    HashMap<String, ObjectOutputStream>
            maps = new HashMap<String, ObjectOutputStream>();
    HashMap<String, String>
            ids = new HashMap<String, String>();

    ArrayAdapter<String> adapter;

    ServerSocket serverSocket;
    boolean aflag = true;
    int port = 8888;

    Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeUi();
        new serverReady().start();
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
                            setList();
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
    }

    class Receiver extends Thread{

        InputStream is;
        ObjectInputStream ois;

        OutputStream os;
        ObjectOutputStream oos;

        Socket socket;
        public Receiver(Socket socket) throws IOException {
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

    public void makeUi() {
        listView = findViewById(R.id.listView);
        c1Id = findViewById(R.id.c1Id);
        c2Id= findViewById(R.id.c2Id);
        c3Id = findViewById(R.id.c3Id);
        c4Id = findViewById(R.id.c4Id);
        c1Data = findViewById(R.id.c1Data);
        c2Data = findViewById(R.id.c2Data);
        c3Data = findViewById(R.id.c3Data);
        c4Data = findViewById(R.id.c4Data);
        listView = findViewById(R.id.listView);
    }
    public void ckbt(View v){
        Msg msg = null;
        if(v.getId() == R.id.button){
            msg = new Msg("server","1",null);
        }else if(v.getId() == R.id.button2){
            msg = new Msg("server","0",null);
        }
        sendMsg(msg);
    }
}


