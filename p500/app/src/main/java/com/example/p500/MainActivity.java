package com.example.p500;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText, editText2;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        progressDialog = new ProgressDialog(this);
    }
    public void login (View v) {
        String id = editText.getText().toString();
        String pwd = editText2.getText().toString();
        HttpTask task = new HttpTask(id,pwd);
        task.execute();


    }
    class HttpTask extends AsyncTask<Void, Void, String> {

        String url ;

        public HttpTask(String id, String pwd){
            url = "http://70.12.113.248/webview/login.jsp?";
            url += "id="+id+"&pwd="+pwd;
        }

        //thread와 동일 형식
        @Override //시작하기전
        protected void onPreExecute() {
            progressDialog.setTitle("HTTP Conndection...");
            progressDialog.setTitle("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override // 시작
        protected String doInBackground(Void... voids) {
            return HttpHandler.getString(url); //이 로직이 우리가 만든 HttpHandler.java이다
            //이 로직이 있으면 doinbackgroud에서 가져다 쓸 수 있고, 재사용 가능하다.
        }

        @Override //끝날때
        protected void onPostExecute(String s) {

            progressDialog.dismiss();
           if(s.trim().equals("1")) {

               Intent intent =
                       new Intent(getApplicationContext(),
                               SecondActivity.class);
               Log.d("startactivity","start");
               startActivity(intent);
               Log.d("startactivity","end");
//               SecondActivity를 띄운다

           }else {
               Toast.makeText(MainActivity.this,"틀렸습니다.", Toast.LENGTH_SHORT);
           }

        }


    }

}
