package com.dy.www.slideview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dy.www.library.DragRelativeLayout;
import com.dy.www.library.SlideView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drl_dragView)
    DragRelativeLayout dragRelativeLayout;

    @BindView(R.id.rv)
    RecyclerView rv;

    private Context context;
    private Adapter adapter;
    private List<Map<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initData();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        adapter = new Adapter(context, list);
        rv.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 0; i < 20 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", i);
            list.add(map);
        }
    }

}
