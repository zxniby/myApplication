package com.example.xiangnanzhang.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by XiangnanZhang on 22/09/15.
 */
public class MyView extends View {

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Rect mBound;
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet,0);
    }

    public MyView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet,R.styleable.MyView,defStyle,0);
        int n = a.getIndexCount();
        for(int i = 0; i < n; i++){
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyView_titleText:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.MyView_titleTextColor:
                    //default size is black
                    mTitleTextColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MyView_titleSize:
                    //default size is 16sp, TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }


    @Override
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int width= 0, height = 0;
//        int desireWidth = 0, desireHeight = 0;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText,getWidth()/2 - mBound.width()/2, getHeight()/2+mBound.height()/2, mPaint);
    }



}
