package com.example.p500;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Item> lists = new ArrayList<>();
    LinearLayout container;
    ItemAdapter itemAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        getData();
        itemAdapter = new ItemAdapter(lists);
        listView.setAdapter(itemAdapter);

    }

    private void getData() {
        lists.add(new Item(R.drawable.c1,"유한솔","010-0000-0000"));
         lists.add(new Item(R.drawable.c2,"문한나","010-1111-0000"));
        lists.add(new Item(R.drawable.c3,"김나윤","010-2222-0000"));
         lists.add(new Item(R.drawable.c4,"김완규","010-3333-0000"));
          lists.add(new Item(R.drawable.icon,"박나영","010-4444-0000"));
           lists.add(new Item(R.drawable.c1,"한솔팀장","010-5555-0000"));
           lists.add(new Item(R.drawable.c2,"김한솔","010-6666-0000"));
           lists.add(new Item(R.drawable.c3,"독고한나","010-7777-0000"));
            lists.add(new Item(R.drawable.c4,"남궁한나","010-8888-0000"));
         lists.add(new Item(R.drawable.icon,"이한나","010-9999-0000"));

    }

    class ItemAdapter extends BaseAdapter {
        ArrayList<Item> lists;

        public ItemAdapter(){

        }
        public ItemAdapter(ArrayList<Item> lists){
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater =
                    (LayoutInflater)getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE
                    );
            itemView = inflater.inflate(
                    R.layout.list_layout,
                    container,
                    true
            );
            ImageView
                    img = itemView.findViewById(R.id.imageView);
            TextView
                    name = itemView.findViewById(R.id.textView);
            TextView
                    phone = itemView.findViewById(R.id.textView2);

            Item item = lists.get(position);
            img.setImageResource(item.getImg());
            name.setText(item.getName());
            phone.setText(item.getPhone());

            return itemView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                int result =
                        data.getIntExtra("result",0);

            }
        }
    }
}
