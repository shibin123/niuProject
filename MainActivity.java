package com.ricefieldpro;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;


import com.ricefieldpro.test.AutoScrollRecyclerView;
import com.ricefieldpro.view.AutoPollAdapter;
import com.ricefieldpro.view.AutoPollRecyclerView;
import com.ricefieldpro.view.ScrollSpeedLinearLayoutManger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import rice.com.library.BaseActivity;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends BaseActivity {
    RecyclerView recyclerView;


    private LinearSmoothScroller mScroller;
    private Disposable mAutoTask;
    private AutoScrollRecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvTip = findViewById(R.id.tv_tip);

        Typeface fromAsset = Typeface.createFromAsset(getResources().getAssets(), "fonts/fangzheng.ttf");
        tvTip.setTypeface(fromAsset);


        planD();
    }


    void planD() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("测试数据" + i);
        }

        ScrollAdapter adapter = new ScrollAdapter(data, MainActivity.this);

        recyclerView = findViewById(R.id.recyclerView);
        mRv.setLayoutManager(new LinearLayoutManager(this));
//        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRv.setAdapter(adapter);

//        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
//        defaultItemAnimator.setAddDuration(1000);
//        defaultItemAnimator.setRemoveDuration(1000);
//        mRv.setItemAnimator(defaultItemAnimator);

        //item滚动步骤1：自定义LinearSmoothScroller，重写方法，滚动item至顶部，控制滚动速度
        mScroller = new LinearSmoothScroller(this) {

            //将移动的置顶显示
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

            //控制速度，这里注意当速度过慢的时候可能会形成流式的效果，因为这里是代表移动像素的速度，
            // 当定时器中每隔的2秒之内正好或者还未移动一个item的高度的时候会出现，前一个还没移动完成又继续移动下一个了，就形成了流滚动的效果了
            // 这个问题后续可通过重写另外一个方法来进行控制，暂时就先这样了
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 3f / displayMetrics.density;
            }
        };


//        recyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
//            @Override
//            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
//                super.smoothScrollToPosition(recyclerView, state, position);
//                LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
//                    @Override
//                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
//                        return 3f / displayMetrics.density;
//                    }
//                };
//
//                linearSmoothScroller.setTargetPosition(position);
//                startSmoothScroll(linearSmoothScroller);
//            }
//        };
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            list.add(i + "");
//        }
//        recyclerView.setAdapter(new ScrollAdapter(list, MainActivity.this));

    }

    void start1() {
        if (mAutoTask!= null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
        }
        mAutoTask = Observable.interval(1, 2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                //滚动到指定item
                mScroller.setTargetPosition(aLong.intValue());
                mRv.getLayoutManager().startSmoothScroll(mScroller);
            }
        });


    }

    void stop1() {
        if (mAutoTask!= null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
            mAutoTask = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop1();
    }

    @Override
    protected void onStart() {
        super.onStart();
        start1();
    }


    void planC() {
//
//        recyclerView = findViewById(R.id.autoPollRecyclerView);
//        ScrollSpeedLinearLayoutManger layoutManager = new ScrollSpeedLinearLayoutManger(this);
//
//        layoutManager.setSmoothScrollbarEnabled(true);
//
//        layoutManager.setAutoMeasureEnabled(true);
//
//        recyclerView.setLayoutManager(layoutManager);// 布局管理器。
//
//        recyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
//
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            list.add(i + "");
//        }
//        recyclerView.setAdapter(new AutoPollAdapter(MainActivity.this, list));

//        recyclerView.start();
//
//        recyclerView.doTopGradualEffect(500);
    }

    /**
     * ViewFlipper
     */
    void planA() {
        ViewFlipper vf1 = (ViewFlipper) findViewById(R.id.marquee_view1);
        vf1.addView(View.inflate(this, R.layout.notice1, null));
    }

    /**
     *
     */
    LinearLayout viewRank;
    int a = 100000;

    void planB() {
        viewRank = findViewById(R.id.view_rank);

        start();
    }

    void addView() {
        TextView textView = new TextView(this);
        textView.setHeight(100);
        textView.setTextSize(20);
        textView.setWidth(WRAP_CONTENT);
        textView.setBackgroundResource(R.drawable.bg_rank);
        textView.setText(a++ + "");
        textView.setPadding(20, 5, 20, 5);


        viewRank.addView(textView);
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 90, 0);
        translateAnimation.setDuration(1000);
        textView.setAnimation(translateAnimation);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(0, 20, 0, 0);
        textView.setLayoutParams(layoutParams);
        setAnimal();

        setBgAlpha();
        viewRank.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewRank.getChildCount() > 4) {
                    viewRank.removeViews(0, 1);
                }
            }
        }, 1000);

    }

    void setAnimal() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -90);
        translateAnimation.setDuration(1000);
        if (viewRank.getChildCount() > 3) {
            TextView textView = (TextView) viewRank.getChildAt(0);
            textView.setAnimation(translateAnimation);
            TextView textView2 = (TextView) viewRank.getChildAt(1);
            textView2.setAnimation(translateAnimation);
            TextView textView3 = (TextView) viewRank.getChildAt(2);
            textView3.setAnimation(translateAnimation);
            TextView textView4 = (TextView) viewRank.getChildAt(3);
            textView4.setAnimation(translateAnimation);
        }
    }

    void setBgAlpha() {
        if (viewRank.getChildCount() > 3) {
            TextView textView = (TextView) viewRank.getChildAt(0);
            textView.setAlpha(0.3f);
            TextView textView2 = (TextView) viewRank.getChildAt(1);
            textView2.setAlpha(0.5f);
            TextView textView3 = (TextView) viewRank.getChildAt(2);
            textView3.setAlpha(0.7f);
        }
    }

    void start() {
        Observable.interval(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        addView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    void startTask() {
        Observable.interval(2, TimeUnit.SECONDS)
                .takeWhile(aLong -> aLong == 15)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                        Log.e("niu", "aLong = " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
