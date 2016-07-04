package com.jw.wq.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Sight-WXC on 2016/6/30 0030.
 * <p>
 * 封装的OkHttp的请求
 */
public class ServerRetrofit {


    public static final int pageize = 10;


    public ServerApi serverApi;


    final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    ServerRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(12*100, TimeUnit.SECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://gank.io/api")
                .setConverter(new GsonConverter(gson));

        RestAdapter restAdapter = builder.build();
        serverApi = restAdapter.create(ServerApi.class);
        com.jw.sight.utlis.Logger.e("server api  create");

    }

    public ServerApi getServerApi() {
        return serverApi;
    }
}
