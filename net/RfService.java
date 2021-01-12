package com.ricefieldpro.net;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by niu on 2020/12/10
 **/
public interface RfService {

    @POST("gallery/info/v1")
    @FormUrlEncoded
    Observable<Object> getFirstData(@Field("gallery_id") String gallery_id);

}
