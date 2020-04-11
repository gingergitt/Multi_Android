package com.example.p500;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class HttpHandler {
    public static String getString(String urlstr) {
        //http 프로토콜을 구현하는 것이다.
        String result = null;
        URL url = null;
        HttpURLConnection hcon = null;
        InputStream is = null;
        try {
            url = new URL(urlstr);
            hcon = (HttpURLConnection) url.openConnection();
            hcon.setConnectTimeout(2000);
            hcon.setRequestMethod("GET"); //POST도 가능
            is = new BufferedInputStream(hcon.getInputStream());
            // -> url객체 만들고, open하면 연결됨, 객체만들고 inputstream해서
            // 서버를 연결할 객체를 만듦?

            //여기 is(inputstream)에서 결과값을 꺼낸다.
            result = convertStr(is);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    public static String convertStr(InputStream is){
        String result = null;
        BufferedReader bi = null;
        StringBuilder sb = new StringBuilder();
        try{
            bi = new BufferedReader(
                    new InputStreamReader(is)
            );
            String temp = "";
            while((temp =bi.readLine()) != null){
                sb.append(temp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
