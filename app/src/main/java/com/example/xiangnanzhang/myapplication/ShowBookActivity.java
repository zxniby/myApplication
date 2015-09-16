package com.example.xiangnanzhang.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by XiangnanZhang on 16/09/15.
 */
public class ShowBookActivity extends Activity {

    TextView tv_id, tv_name, tv_publisher;
    Book mBook;
    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_show_book);
        init();
    }

    private void init(){
        mBook = getIntent().getParcelableExtra("BookTest");
        tv_id = (TextView)findViewById(R.id.tv_id);
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_publisher = (TextView)findViewById(R.id.tv_publisher);
        Log.i("mBook+++++++++++++++",mBook.getbName());
        tv_id.setText(Integer.toString(mBook.getbId()));
        tv_name.setText(mBook.getbName());
        tv_publisher.setText(mBook.getbPublisher());
    }


}
