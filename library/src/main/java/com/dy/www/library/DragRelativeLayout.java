package com.dy.www.library;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by dy on 2017/3/31.
 */

public class DragRelativeLayout extends RelativeLayout implements SlideView.OnFooterChangedListener{
    private int minHeight;
    private int maxHeight;
    private SlideView footerView;
    private Context context;
    private boolean firstLayout = true;

    public DragRelativeLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public DragRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public DragRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        minHeight = 0;
        setBackgroundColor(Color.GREEN);
        setFooterView(new SlideView(context));
    }

    public View getFooterView() {
        return footerView;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (firstLayout) {
            maxHeight = b - t;
            if (footerView != null) {
                if (footerView.getViewHeight() > maxHeight) {
                    throw new IllegalArgumentException("slideView's height can't higher than it's parent");
                }
                footerView.setMaxBottomLocation(maxHeight);
            }
            firstLayout = false;
        }

    }

    private void setFooterView(SlideView view) {
        footerView = view;
        footerView.setOnFooterChangedListener(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, footerView.getViewHeight());
        lp.addRule(ALIGN_PARENT_BOTTOM);
        footerView.setLayoutParams(lp);
        addView(footerView);
    }


    @Override
    public boolean onFooterChanged(int topLocation, int bottomLocation) {
        if (topLocation < minHeight || bottomLocation > maxHeight) {
            return false;
        }
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = bottomLocation - getTop();
        setLayoutParams(lp);
        return true;
    }
}
