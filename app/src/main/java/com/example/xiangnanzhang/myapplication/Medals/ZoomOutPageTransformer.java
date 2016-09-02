package com.example.xiangnanzhang.myapplication.Medals;

/**
 * Created by XiangnanZhang on 7/09/15.
 */

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer
{
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

//    NativeBlurProcess nativeBlurProcess = new NativeBlurProcess();
    @SuppressLint("NewApi")
    public void transformPage(View view, float position)
    {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        Log.e("TAG", view + " , " + position + "");

        if (position < -1)
        { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0.2f);
//            nativeBlurProcess.blur(ConvertToBitmap(view),100);
//            javaBlurProcess.blur(ConvertToBitmap(view),100);
//            Fastblur.onBlur(bitmap,0.2f,10);

        } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
        { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            Log.d("vertMargin",Float.toString(vertMargin));
            Log.d("horzMargin",Float.toString(horzMargin));
            if (position < 0)
            {
                view.setTranslationX(horzMargin - vertMargin / 2);
                Log.d("++++++toleft++++", Float.toString(horzMargin - vertMargin / 2));
            } else
            {
                view.setTranslationX(-horzMargin + vertMargin / 2);
                Log.d("++++++toright++++", Float.toString(-horzMargin - vertMargin / 2));
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                    / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else
        { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0.2f);
        }
    }

    protected Bitmap ConvertToBitmap(View view) {

        Bitmap map;

        view.setDrawingCacheEnabled(true);

        view.buildDrawingCache();

        return map=view.getDrawingCache();

    }
}