package com.acode.selectedview;

import android.util.Log;

/**
 * user:yangtao
 * date:2018/3/211452
 * email:yangtao@bjxmail.com
 * introduce:功能
 */
public class DLog {
    public static void i(Class clazz, String msg) {
        Log.d(clazz.getSimpleName() + "|beijixing|", msg);
    }
}
