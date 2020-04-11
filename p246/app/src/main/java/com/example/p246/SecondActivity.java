package com.example.p246;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView2;
    int nums [] = {1,2,3,4,5,6,7,8,9,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView2 = findViewById(R.id.textView2);
        Intent intent = getIntent();
        int num = intent.getIntExtra("num",0);
        textView2.setText(textView2.getText().toString()+num);


    }
    public void ckbt(View v){
        if(v.getId() == R.id.button2){
            finish();
        }else if(v.getId() == R.id.button3){
            Intent intent =
                    new Intent(getApplicationContext(),
                            ThirdActivity.class);
            intent.putExtra("nums",nums);
            startActivityForResult(intent,101);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                int result =
                data.getIntExtra("result",0);
                textView2.setText("Result:"+result);
            }
        }
    }
}


