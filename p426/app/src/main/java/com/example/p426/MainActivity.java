package com.example.p426;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
        //data 뿌리기
    ArrayList<String> datas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= findViewById(R.id.listView);
        getData();
        //adapter가 가공을해서, 그것을 list 에 뿌린다
        //list에 data를 뿌리는 역할이 바로 adapter이다.(번거롭지않도록 안드로이드에서 제공)
        // data -> adapter -> list 로 흘러간다.
        // list는 반드시 adapter를 통해서 data를 얻어 뿌려야 함.
        //ArrayAdapter : 여러개의 데이터를 집어 넣을 때 사용
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
        //이벤트 붙이기 (new 쓰고 ctrl+space하면 자동완성)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT);
                AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hi");
                builder.setMessage("Are you deleted this Item");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //data를 삭제해야한다.(지우기)
                        datas.remove(position);
                        //data삭제 후 listView를 다시 뿌려주어야 한다.
                        // data가 삭제되면 adapter에게자동으로 데이터변경을 알린다.
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.show();
            }
        });
    }

    //가상의 data 만들기
    private void getData() {
        datas= new ArrayList<>();
        for(int i=0; i<20; i++){
            datas.add("Item...."+i);
        }

    }

    public void ckbt(View v) {
        if(v.getId() == R.id.button){

        }else if(v.getId()==R.id.button2){

        }
    }
}
