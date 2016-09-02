package com.example.xiangnanzhang.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialActivity extends Activity {

    private EditText et_dial;
    private Button ok_btn;
    private Context mContext;
    private TextView tv_json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        mContext = this;
        et_dial = (EditText)findViewById(R.id.et_dialNumber);
        ok_btn = (Button)findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Dialing")
                        .setMessage("Are you going to dial "+et_dial.getText())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Uri uri = Uri.parse("tel:" + et_dial.getText());
//                                Intent i = new Intent(Intent.ACTION_DIAL, uri);
                                Uri uri = Uri.parse("geo:38.899533,-77.036476");
                                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

            }
        });
        tv_json = (TextView)findViewById(R.id.tv_json);
        tv_json.setText(jsonInit());

    }

    public String jsonInit(){
        try{
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            android.net.wifi.WifiManager wm = (android.net.wifi.WifiManager)this.getSystemService(Context.WIFI_SERVICE);
            Log.d("good","++++++good+++++");
            String device_id = tm.getDeviceId();
            String macAddr = wm.getConnectionInfo().getMacAddress();
            Log.d("device_id",device_id);
            Log.d("mac address", macAddr);
            json.put("device_id",device_id);
            json.put("mac address", macAddr);
            return json.toString();
        } catch(Exception e){
            e.printStackTrace();
        }
        return "null";

    }




}
