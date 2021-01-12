package com.ricefieldpro.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by kermitye on 2018/10/23 17:41
 */
public class AutoScrollRecyclerView extends RecyclerView {


    private Disposable mAutoTask;
    Subscription subscription;

    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        return 0f;
    }

    public void start() {
        if (mAutoTask != null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
        }

        mAutoTask = Observable.interval(1000, 100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        smoothScrollBy(0, 20);
                    }
                });
    }

    public void stop() {
        if (mAutoTask != null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
            mAutoTask = null;
        }
    }


    //禁止手动滑动
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return true;
    }
}
