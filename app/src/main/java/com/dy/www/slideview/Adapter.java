package com.dy.www.slideview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dy on 2017/4/5.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<Map<String, Object>> list = new ArrayList<>();
    private Context context;

    Adapter(Context context,List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).get("name").toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        ViewHolder (View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv);
        }
    }

}
