package com.example.xiangnanzhang.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button mBtn_parcel, mBtn_webview, mBtn_medals,mBtn_scrolllistview,mBtn_onMeasure,mBtn_dialog,mBtn_dialing,mBtn_get;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        mBtn_parcel = (Button)findViewById(R.id.btn_parcel);
        mBtn_parcel.setOnClickListener(this);
        mBtn_webview = (Button)findViewById(R.id.btn_webview);
        mBtn_webview.setOnClickListener(this);
        mBtn_medals = (Button)findViewById(R.id.btn_medals);
        mBtn_medals.setOnClickListener(this);
        mBtn_scrolllistview = (Button)findViewById(R.id.btn_scrolllistview);
        mBtn_scrolllistview.setOnClickListener(this);
        mBtn_onMeasure = (Button)findViewById(R.id.btn_onMeasure);
        mBtn_onMeasure.setOnClickListener(this);
        mBtn_dialog = (Button)findViewById(R.id.btn_dialog);
        mBtn_dialog.setOnClickListener(this);
        mBtn_dialing = (Button)findViewById(R.id.btn_dialing);
        mBtn_dialing.setOnClickListener(this);

        mBtn_get = (Button)findViewById(R.id.btn_get);
        mBtn_get.setOnClickListener(this);

    }



    @Override
    public void onClick(View view){
        int viewId = view.getId();
        Intent i = new Intent();
        switch (viewId) {
            case R.id.btn_parcel:
                Book book = new Book(1,"Thinking in Java","USP");
                i.setClass(mContext,ShowBookActivity.class);
                i.putExtra("BookTest", book);
                startActivity(i);
                break;
            case R.id.btn_webview:
                i.setClass(mContext, ShowWebView.class);
                startActivity(i);
                break;

            case R.id.btn_medals:
                i.setClass(mContext, MedalsActivity.class);
                startActivity(i);
                break;

            case R.id.btn_scrolllistview:
                i.setClass(mContext, ImgBydListViewActivity.class);
                startActivity(i);
                break;

            case R.id.btn_onMeasure:
                i.setClass(mContext, OnMeasureActivity.class);
                startActivity(i);
                break;

            case R.id.btn_dialog:
                i.setClass(mContext, DialogActivity.class);
                startActivity(i);
                break;

            case R.id.btn_dialing:
                i.setClass(mContext, DialActivity.class);
                startActivity(i);
                break;

            case R.id.btn_get:
                i.setClass(mContext,HttpRequestActivity.class);
                startActivity(i);
                break;
        }
    }
}
