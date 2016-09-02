package com.example.xiangnanzhang.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by XiangnanZhang on 21/09/15.
 */
public class ImgBydListViewActivity extends Activity {

    private ListView mListView;
    private LinearLayout mTop, mParent;

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_topimage_downlistview);
        mListView = (ListView)findViewById(R.id.lv_honors);
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);

        mParent = (LinearLayout)findViewById(R.id.ll_parent);
        mTop = (LinearLayout)findViewById(R.id.ll_top);
        mParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mParent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mTop.getLayoutParams().height = mParent.getHeight();
                mListView.getLayoutParams().height = mParent.getHeight();
                mTop.invalidate();
                mListView.invalidate();
            }
        });

    }

    public class MyAdapter extends BaseAdapter {
        String[] dates = new String[]{"1991-1992","1992-1993","1993-1994","1995-1996","1996-1997","1997-1998","1","2","3","4","5","6","7","8","9","10","1","2","3","4","5","6","7","8","9","10"};
        String[] honors = new String[]{"NBA Championship","NBA Championship","NBA Championship","NBA Championship","NBA Championship","NBA Championship","1","2","3","4","5","6","7","8","9","10","1","2","3","4","5","6","7","8","9","10"};
        LayoutInflater inflater;
        @Override
        public int getCount(){
            return dates.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {

            ViewHolder viewHolder;
            if(convertView!=null) {
                viewHolder = (ViewHolder)convertView.getTag();
            }else {
                inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_honor,null);
                viewHolder = new ViewHolder();
                viewHolder.mTv_date = (TextView)convertView.findViewById(R.id.tv_date);
                viewHolder.mTv_date.setTextSize(20);
                viewHolder.mTv_honor = (TextView)convertView.findViewById(R.id.tv_honor);
                viewHolder.mTv_honor.setTextSize(20);
                convertView.setTag(viewHolder);
            }
            viewHolder.mTv_date.setText(dates[position]);
            viewHolder.mTv_honor.setText(honors[position]);
            return convertView;

        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public String getItem(int position) {
            return dates[position];
        }

    }

    public class ViewHolder{
        private TextView mTv_date;
        private TextView mTv_honor;


    }
}
