package com.jw.wq.http;

/**
 * Created by Sight-WXC on 2016/7/1 0001.
 */
public class ServerFactory {

    protected static final Object monitor = new Object();
    static ServerApi sGankIOSingleton = null;

    public static ServerApi getServerApi() {
        synchronized (monitor) {
            if (sGankIOSingleton == null) {
                sGankIOSingleton = new ServerRetrofit().getServerApi();
            }
            return sGankIOSingleton;
        }
    }
}
