package com.example.p246;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ckbt(View v){
        Intent intent =
                new Intent(getApplicationContext(),
                SecondActivity.class);
    //    intent.putExtra("num",100);
        startActivity(intent);

    }
}




