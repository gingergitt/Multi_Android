package com.example.p287;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class View1Fragment extends Fragment {

    EditText editText, editText2;
    Button button4;
    TextView textView;
    public View1Fragment() {
        // Required empty public constructor
    }


    @Override
    //onCreateView 가 화면을 그려준다.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //viewGroup이 리턴되서 xml 화면 버튼 아래 가운데 화면에 나오게 됨
        // attachtoroot:false
        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_view1,container,false);
        editText = viewGroup.findViewById(R.id.editText);
        editText2 = viewGroup.findViewById(R.id.editText2);
        button4 = viewGroup.findViewById(R.id.button4);
        textView = viewGroup.findViewById(R.id.textView);
        button4.setOnClickListener(new View.OnClickListener());

        @Override
        public void onClick(View v){
            String id = editText.getText().toString();
            String pwd = editText2.getText().toString();
            textView.setText(id+" "+pwd);


        }
        return viewGroup;




    }

    public void setText(String str) {
        textView.setText(str);
    }
}
