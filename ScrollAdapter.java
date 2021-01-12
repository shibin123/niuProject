package com.ricefieldpro;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by niu on 2021/1/4
 **/
public class ScrollAdapter extends BaseAdapter<String> {
    public ScrollAdapter(List<String> data, Context context) {
        super(data, context);
    }

    @Override
    protected int setViewLayoutId() {
        return R.layout.notice1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindViewHolder(holder, mData.get(position % mData.size()));
//        rotateAnimRun(holder.getView(R.id.root));
    }

    protected void onBindViewHolder(ViewHolder holder, String s) {
        TextView text = holder.getView(R.id.text);
        text.setText(s);

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

//    public static void rotateAnimRun(View view) {
//        ObjectAnimator//
//                .ofFloat(view, "alpha", 1.0F, 0.3F)//
//                .setDuration(4000)//
//                .start();
//    }
}
