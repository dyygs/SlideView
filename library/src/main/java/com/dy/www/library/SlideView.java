package com.dy.www.library;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by dy on 2017/3/31.
 */

public class SlideView extends RelativeLayout {
    private int mLastY;
    private int maxBottomLocation;
    private boolean isFirstMove = true;
    private int scrollSpeed = 60;
    private int scrollDuration = 300;
    private int height = 70;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetY = y - mLastY;
                int topLocation;
                int bottomLocation;
                topLocation = getTop() + offsetY;
                bottomLocation = getBottom() + offsetY;
                if (offsetY > scrollSpeed) {
                    if (isFirstMove) {
                        //滑动到底部
                        scroll(topLocation, maxBottomLocation - 70);
                        isFirstMove = false;
                    }
                }
                if (offsetY < -scrollSpeed) {
                    if (isFirstMove) {
                        //滑动到顶部
                        scroll(topLocation, 0);
                        isFirstMove = false;
                    }

                }
                //调整layout的四个坐标
                if (onFooterChangedListener != null) {
                    boolean canMove = onFooterChangedListener.onFooterChanged(topLocation, bottomLocation);
                    if (canMove) {
                        layout(getLeft(), topLocation, getRight(), bottomLocation);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                isFirstMove = true;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    private OnFooterChangedListener onFooterChangedListener;

    public void setOnFooterChangedListener(OnFooterChangedListener onFooterChangedListener) {
        this.onFooterChangedListener = onFooterChangedListener;
    }


    public interface OnFooterChangedListener {
        boolean onFooterChanged(int topLocation, int bottomLocation);
    }

    public void setMaxBottomLocation(int maxBottomLocation) {
        this.maxBottomLocation = maxBottomLocation;
    }

    private void scroll(final int topLocation, int endLocation) {
        ValueAnimator va = ValueAnimator.ofInt(topLocation, endLocation);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int top =(Integer)valueAnimator.getAnimatedValue();
                int bottom = top + 70;
                //动态更新view的高度
                if (onFooterChangedListener != null) {
                    boolean canMove = onFooterChangedListener.onFooterChanged(top, bottom);
                    if (canMove) {
                        layout(getLeft(), top, getRight(), bottom);
                    }
                }
            }
        });
        va.setDuration(scrollDuration);
        //开始动画
        va.start();
    }

    public int getScrollSpeed() {
        return scrollSpeed;
    }

    public void setScrollSpeed(int scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    public int getScrollDuration() {
        return scrollDuration;
    }

    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
    }

    public int getViewHeight() {
        return height;
    }

    public void setViewHeight(int height) {
        this.height = height;
    }
}
