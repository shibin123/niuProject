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


import com.google.gson.Gson;
import com.ricefieldpro.bean.Channel;
import com.ricefieldpro.bean.Content;
import com.ricefieldpro.bean.Plan;
import com.ricefieldpro.bean.PlayList;
import com.ricefieldpro.test.AutoScrollRecyclerView;
import com.ricefieldpro.utils.FileUtils;
import com.ricefieldpro.view.AutoPollAdapter;
import com.ricefieldpro.view.AutoPollRecyclerView;
import com.ricefieldpro.view.ScrollSpeedLinearLayoutManger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvTip = findViewById(R.id.tv_tip);

        Typeface fromAsset = Typeface.createFromAsset(getResources().getAssets(), "fonts/fangzheng.ttf");
        tvTip.setTypeface(fromAsset);

        tvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.fromCallable(new Callable<Plan>() {
                    @Override
                    public Plan call() throws Exception {

                        String jsonStr = FileUtils.getJsonStr(MainActivity.this, "plan.json");
                        Gson gson = new Gson();
                        Plan plan = gson.fromJson(jsonStr, Plan.class);
                        return plan;
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Plan>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Plan plan) {

                                List<Content> contentList = plan.getPlayerScreen().getContentList();
                                Map<String, Content> contentHashMap = plan.getPlayerScreen().getContentHashMap();

                                List<PlayList> playlists = plan.getPlayerScreen().getPlaylists();
                                Map<String, PlayList> playHashMap = plan.getPlayerScreen().getPlayHashMap();

                                Channel channel = plan.getPlayerScreen().getChannel();



                            }

                            @Override
                            public void onError(Throwable e) {

                                Log.e("niu", e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });


    }

}
