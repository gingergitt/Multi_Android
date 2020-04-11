package com.example.p168;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    LinearLayout b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);


    }

    public void ck(View v) {
        if(v.getId()==R.id.b1) {
            linear1.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.INVISIBLE);
            linear3.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.b2) {
            linear1.setVisibility(View.INVISIBLE);
            linear2.setVisibility(View.VISIBLE);
            linear3.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.button3){
            linear1.setVisibility(View.INVISIBLE);
            linear2.setVisibility(View.INVISIBLE);
            linear3.setVisibility(View.VISIBLE);
        }

    }
}
