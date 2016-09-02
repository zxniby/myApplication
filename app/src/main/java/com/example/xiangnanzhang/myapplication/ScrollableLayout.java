package com.example.xiangnanzhang.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.OverScroller;

/**
 * Created by XiangnanZhang on 21/09/15.
 */
public class ScrollableLayout extends LinearLayout {
    private float mFloatLastY;
    private int mIntScrollY;
    private OverScroller mScroller;
    private int mTouchSlot;
    private int mMaximumVelocity, mMinimumVelocity;
    private VelocityTracker mVelocityTracker;
    private int velocityY;
    private boolean isTopHidden = false, isInControl=false, isDragging = false;
    private ListView innerListView;
    private View mTop,mMid;


    public ScrollableLayout(Context context) {
        super(context);
        init(context);
    }

    public ScrollableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        mScroller = new OverScroller(context);
        mTouchSlot = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    private void initVelocityTracker(){
        if(mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
    }

    private void recycleVelocityTracker() {
        if(mVelocityTracker != null)
            mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    @Override
    public void computeScroll(){
        if(mScroller.computeScrollOffset()) {
            scrollTo(0,mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = (View)findViewById(R.id.tv_ajImg);
        mMid = (View)findViewById(R.id.tv_honor);
        innerListView = (ListView)findViewById(R.id.lv_honors);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        int action = ev.getAction();
        float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mFloatLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                float dy = y - mFloatLastY;
                View child1 = innerListView.getChildAt(innerListView.getFirstVisiblePosition());
                Log.i("isTopHidden",Boolean.toString(isTopHidden));
                Log.i("child1!=null",Boolean.toString(child1 != null));
                Log.i("child1.getTop() == 0",Boolean.toString(child1.getTop() == 0));
                Log.i("dy > 0",Boolean.toString(dy > 0));
                Log.i("isInControl",Boolean.toString(isInControl));
                if (!isInControl && child1 != null && child1.getTop() == 0 && dy > 0 && isTopHidden) {
                    isInControl = true;
                    MotionEvent ev1 = MotionEvent.obtain(ev);
                    ev1.setAction(MotionEvent.ACTION_CANCEL);
                    dispatchTouchEvent(ev1);
                    ev1.setAction(MotionEvent.ACTION_DOWN);
                    return dispatchTouchEvent(ev1);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("dispatchTouchEvent","ACTION_UP");
                break;


        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mFloatLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                View child1 = innerListView.getChildAt(innerListView.getFirstVisiblePosition());
                float dy = y - mFloatLastY;
                mIntScrollY = getScrollY();
                if (Math.abs(dy) > mTouchSlot) {
                    isDragging = true;
                    if(!isTopHidden || isTopHidden && child1 != null && child1.getTop() == 0 && dy >0) {
                        mFloatLastY = y;
                        return true;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDragging = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();
        initVelocityTracker();
        mVelocityTracker.addMovement(ev);
        mIntScrollY = getScrollY();


        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished())
                    mScroller.abortAnimation();
                mFloatLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                float dy = y - mFloatLastY;
                if (Math.abs(dy) > mTouchSlot && !isDragging) {
                    isDragging = true;
                }
                if (isDragging){
                    scrollBy(0, (int) -dy);
                    mFloatLastY = y;
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:
                Log.i("onTouchEvent","ACTION_UP");
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                velocityY = (int)mVelocityTracker.getYVelocity();
                Log.i("VelocityY",Integer.toString(velocityY));
                recycleVelocityTracker();
                if(Math.abs(velocityY) > mMinimumVelocity) {
                   if(velocityY > 0) {
                       mScroller.startScroll(0,getScrollY(),0,-getScrollY(),1000);
                   }
                   else if(velocityY < 0) {
                       mScroller.startScroll(0,getScrollY(),0,getHeight()-getScrollY(), 1000);
                   }
                }else {
                    int diff = getHeight() - getScrollY();
                    if(getScrollY() >= diff) {
                        mScroller.startScroll(0,getScrollY(),0,getHeight()-getScrollY(), 1000);
                    }
                    else{
                        mScroller.startScroll(0,getScrollY(),0,-getScrollY(),1000);
                    }
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                isDragging = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
        }
        return super.onTouchEvent(ev);
    }


    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > getHeight()) {
            y = getHeight();
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }

        isTopHidden = getScrollY() == getHeight();
    }


}
