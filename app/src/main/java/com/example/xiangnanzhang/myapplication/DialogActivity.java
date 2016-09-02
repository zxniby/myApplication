package com.example.xiangnanzhang.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by XiangnanZhang on 25/09/15.
 */
public class DialogActivity extends Activity implements View.OnClickListener{

    private Button btn1,btn2;
    private Context mContext;

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_dialogdemo);

        init();
    }

    private void computeSize(Activity activity){
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int sWidth = outMetrics.widthPixels;
    }
    public void init() {
        mContext = this;
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }



    @Override
    public void onClick(View view){
        int vid = view.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        switch (vid) {
            case R.id.btn_1:
                builder.setTitle("Title")
                        .setMessage("This is Message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(mContext,"OK is clicked",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(mContext,"Cancel is clicked",Toast.LENGTH_LONG).show();
                            }
                        })
                        .create().show();
                break;
            case R.id.btn_2:
                builder.setTitle("图标版")
                        .setMessage("这是图标版")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(mContext, "OK is clicked", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(mContext, "Cancel is clicked", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(R.drawable.dialogicon)
                        .create().show();
                break;

        }
    }
}

