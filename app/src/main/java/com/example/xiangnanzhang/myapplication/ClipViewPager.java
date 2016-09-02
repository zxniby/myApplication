package com.example.xiangnanzhang.myapplication;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by XiangnanZhang on 28/09/15.
 */
public class ClipViewPager extends ViewPager {
    public ClipViewPager(Context context) {
        super(context);
    }

    public ClipViewPager(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_UP) {
            Log.i("ACTION UP","***action up****");
            View view = viewOfClickOnScreee(ev);
            if(view != null) {
                Log.i("ACTION UP","view != null");
                setCurrentItem(indexOfChild(view));
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private View viewOfClickOnScreee(MotionEvent ev) {
        int childCount = getChildCount();
        int[] location = new int[2];
        for(int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            v.getLocationOnScreen(location);
            int minX = location[0];
            int minY = v.getTop();
            int maxX = minX + v.getWidth();
            int maxY = v.getBottom();

            float touchX = ev.getX();
            float touchY = ev.getY();

            if((touchX > minX && touchX < maxX) && (touchY > minY && touchY < maxY)){
                Log.i("ACTION UP",i+" child's been clicked");
                return v;
            }

        }

        return null;
    }
}
