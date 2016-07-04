package com.jw.wq.http;

import com.jw.wq.bean.HomeRespone;
import com.jw.wq.bean.ViedoResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Sight-WXC on 2016/7/1 0001.
 */
public interface ServerApi {


    @GET("/data/福利/" + ServerRetrofit.pageize + "/{page}")
    Observable<HomeRespone> getData(@Path("page") int page);

    @GET("/data/休息视频/" + ServerRetrofit.pageize + "/{page}")
    Observable<ViedoResponse> getViedoData(
            @Path("page") int page);
}
