package com.example.xiangnanzhang.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestActivity extends Activity {


    private Button btn_request;
    private TextView tv_result;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_request);
        tv_result = (TextView)findViewById(R.id.tv_result);
        btn_request = (Button)findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        doRequest();
                    }
                });
            }
        });


    }

    public void doRequest(){

        try{
            URL url = new URL("https://www.baidu.com");
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream im = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(im));
            StringBuilder sb = new StringBuilder();
            String temp = null;
            while((temp = br.readLine()) != null){
                sb.append(temp);
            }

//
//            int count = 0;
//            while(count == 0) {
//                count = im.available();
//            }
//            byte[] b = new byte[count];
//            im.read(b);
            Log.e("++++response+++++", sb.toString());
            tv_result.setText(sb.toString());

            im.close();
//            return sb.toString();
        }catch(Exception e) {
            e.printStackTrace();
            Log.e("e", "error");
        }finally {

        }
//        return null;
    }



}
