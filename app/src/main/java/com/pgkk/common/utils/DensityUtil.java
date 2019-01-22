package com.pgkk.common.utils;

import android.content.Context;

/**
 * Created by tanxueze on 2017/12/12.
 * 单位转换
 */

public class DensityUtil {
    /**
     * dp和px的转换
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
