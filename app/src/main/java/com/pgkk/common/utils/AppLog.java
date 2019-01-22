package com.pgkk.common.utils;


import android.util.Log;

import com.pgkk.BuildConfig;


/**
 * Created by tanxueze on 2017/12/6.
 */

public class AppLog {
    private static final String TAG = "BaseProject";

    public static void init() {

    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG,msg);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG,msg);
        }
    }


    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG,msg);
        }
    }

    public static void e(Throwable msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG,msg.getMessage());
        }
    }
}
