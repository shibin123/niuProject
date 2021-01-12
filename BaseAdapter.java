package com.ricefieldpro;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niu on 2021/1/4
 **/

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    protected Context mContext;

    public List<T> getData() {
        return mData;
    }

    protected List<T> mData = new ArrayList<>();

    public void addData(T t) {
        if (null != t) {
            mData.add(t);
            notifyItemChanged(mData.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addData(List<T> addList) {
        if (addList != null && addList.size() > 0) {
            mData.addAll(addList);
            notifyItemRangeChanged(mData.size() - addList.size(), addList.size());
        }
    }

    public void insertData(List<T> addList) {
        if (addList != null && addList.size() > 0) {
            if (mData.size() < 1) {
                mData.addAll(addList);
            } else {
                mData.addAll(0, addList);
            }
//            notifyDataSetChanged();
            notifyItemRangeChanged(0, addList.size());
        }
    }

    public void clear() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        notifyDataSetChanged();
    }

    public void setDatas(List<T> data) {
        if (null == data) {
            data = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public BaseAdapter(List<T> data, Context context) {
        mContext = context;
        mData = data == null ? (List<T>) new ArrayList<>() : data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(setViewLayoutId(), parent, false));
    }

    protected abstract int setViewLayoutId();

    protected int setViewLayoutId(int viewType) {
        return 0;
    }

    protected abstract void onBindViewHolder(ViewHolder holder, T t);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindViewHolder(holder, mData.get(position));
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @Override
    public int getItemCount() {
        if (null == mData) {
            return 0;
        }
        return mData.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> views = new SparseArray<>();

        public <L extends View> L getView(@IdRes int resId) {
            View view = views.get(resId);
            if (null == view) {
                view = itemView.findViewById(resId);
                views.put(resId, view);
            }
            return (L) view;
        }

        public void setText(@IdRes int resId, CharSequence text) {
            View view = views.get(resId);
            if (null == view) {
                view = itemView.findViewById(resId);
                views.put(resId, view);
            }
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

