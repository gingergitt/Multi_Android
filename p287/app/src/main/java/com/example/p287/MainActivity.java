package com.example.p287;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View1Fragment View1;
    View2Fragment View2;
    View3Fragment View3;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.m6);
        actionBar.setTitle("MYAPP");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_USE_LOGO);
        View1 = new View1Fragment();
        View2 = new View2Fragment();
        View3 = new View3Fragment();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mi1){
            changeView(i:1);
        }else if (item.getItemId()==R.id.mi2) {
            changeView(i:2);
        }else if (item.getItemId() ==R.id.mi3){
            changeView(i:3);
        }
        return super.onOptionsItemSelected(item);
    }

    public void ckbt(View v) {
        if (v.getId() == R.id.button) {

            changeView(1);
        } else if (v.getId() == R.id.button2) {
            changeView(2);

        } else if (v.getId() == R.id.button3) {
            changeView(3);
        }

    }

    public void changeView(int i) {
        if (i == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, View1
            ).commit();
            View1Fragment.setText("-------OK-------");
        } else if (i == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, View2
            ).commit();
           // Log.v(tag:"----------------------",msg:"changeView2");

        } else if (i == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, View3
            ).commit();

        }

    }

}
