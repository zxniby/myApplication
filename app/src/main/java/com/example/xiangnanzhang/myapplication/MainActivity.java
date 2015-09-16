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

public class MainActivity extends Activity {

    Button mBtn_parcel;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListenner();
    }

    private void init(){
        mBtn_parcel = (Button)findViewById(R.id.btn_parcel);
    }

    private void setListenner(){
        mBtn_parcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book(1,"Thinking in Java","USP");
                Intent i = new Intent(mContext,ShowBookActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("BookTest",book);
                i.putExtra("BookTest", book);
                startActivity(i);
            }
        });
    }
}
