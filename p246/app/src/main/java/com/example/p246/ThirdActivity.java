package com.example.p246;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        int [] nums = intent.getIntArrayExtra("nums");
        int sums = 0;
        for(int i:nums){
            sums += i;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("result",sums);
        setResult(RESULT_OK,intent2);
        finish();
    }
    public void ckbt(View v){
        if(v.getId() == R.id.button4){
            finish();
        }else if(v.getId() == R.id.button5){
            Intent intent =
                    new Intent(getApplicationContext(),
                            MainActivity.class);
            startActivity(intent);
        }
    }
}
