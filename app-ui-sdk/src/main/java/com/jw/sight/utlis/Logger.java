package com.jw.sight.utlis;

import android.util.Log;

/**
 * Created  by Sight-WXC
 */
public class Logger {

    /**
     * Library debug tag.
     */
    private static String STag = "com.jw";

    /**
     * Library debug sign.
     */
    private static boolean SDebug = true;

    public static void setTag(String tag) {
        STag = tag;
    }

    public static void setDebug(boolean debug) {
        SDebug = debug;
    }

    public static void i(String msg) {
        if (SDebug)
            Log.i(STag, msg);
    }

    public static void i(Throwable e) {
        if (SDebug)
            Log.i(STag, "", e);
    }

    public static void i(Throwable e, String msg) {
        if (SDebug)
            Log.i(STag, msg, e);
    }

    public static void v(String msg) {
        if (SDebug)
            Log.v(STag, msg);
    }

    public static void v(Throwable e) {
        if (SDebug)
            Log.v(STag, "", e);
    }

    public static void v(Throwable e, String msg) {
        if (SDebug)
            Log.v(STag, msg, e);
    }

    public static void d(String msg) {
        if (SDebug)
            Log.d(STag, msg);
    }

    public static void d(Throwable e) {
        if (SDebug)
            Log.d(STag, "", e);
    }

    public static void d(Throwable e, String msg) {
        if (SDebug)
            Log.d(STag, msg, e);
    }

    public static void e(String msg) {
        if (SDebug)
            Log.e(STag, msg);
    }

    public static void e(Throwable e) {
        if (SDebug)
            Log.e(STag, "", e);
    }

    public static void e(Throwable e, String msg) {
        if (SDebug)
            Log.e(STag, msg, e);
    }

    public static void w(String msg) {
        if (SDebug)
            Log.w(STag, msg);
    }

    public static void w(Throwable e) {
        if (SDebug)
            Log.w(STag, "", e);
    }

    public static void w(Throwable e, String msg) {
        if (SDebug)
            Log.w(STag, msg, e);
    }

    public static void wtf(String msg) {
        if (SDebug)
            Log.wtf(STag, msg);
    }

    public static void wtf(Throwable e) {
        if (SDebug)
            Log.wtf(STag, "", e);
    }

    public static void wtf(Throwable e, String msg) {
        if (SDebug)
            Log.wtf(STag, msg, e);
    }

}