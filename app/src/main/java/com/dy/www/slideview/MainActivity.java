package com.dy.www.slideview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.www.library.DragRelativeLayout;
import com.dy.www.library.SlideView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drl_dragView)
    DragRelativeLayout dragRelativeLayout;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initViews();
    }

    private void initViews() {
        SlideView slideView = new SlideView(context);
        slideView.setBackgroundColor(Color.RED);
        TextView textView = new TextView(context);
        textView.setText("drag me!");
        textView.setGravity(Gravity.CENTER);
        slideView.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        dragRelativeLayout.setFooterView(slideView);
    }
}
