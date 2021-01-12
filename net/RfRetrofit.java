package com.ricefieldpro.net;

import android.content.Context;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import rice.com.library.net.BaseRetrofit;
/**
 * Created by niu on 2020/12/10
 **/
public class RfRetrofit extends BaseRetrofit implements RfService {
    static RfRetrofit mRetrofit;

    Retrofit rfRetrofit;


    String host = "https://api.mkzcdn.com/";

    RfRetrofit(Context context) {
        rfRetrofit = build(context, host);
    }

    public static RfRetrofit getInstance(Context context) {
        if (mRetrofit == null) {
            synchronized (RfRetrofit.class) {
                if (mRetrofit == null) {
                    mRetrofit = new RfRetrofit(context);
                }
            }
        }
        return mRetrofit;
    }


    public RfService getRfService() {
        return rfRetrofit.create(RfService.class);
    }

    @Override
    public Observable<Object> getFirstData(String gallery_id) {
        return getRfService().getFirstData(gallery_id);
    }
}
