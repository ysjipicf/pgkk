package com.pgkk.common.utils.image;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.pgkk.R;
import com.pgkk.common.utils.AppLog;

/**
 * Created by tanxueze on 2017/12/7.
 */

public class ImageLoader {

    public static void load(Context context, Object source, ImageView view){
        Glide.with(context).load(source).centerCrop().into(view);
    }


    /**
     *
     * @param activity
     * @param path  uri,path
     * @param imageView
     * @param width
     * @param height
     */
    public static void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        Glide.with(activity)                             //配置上下文
                .load(Uri.parse(path))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.mipmap.ic_launcher)           //设置错误图片
//                .placeholder(R.mipmap.ic_launcher)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    /**
     *
     * @param activity
     * @param path  uri,path
     * @param imageView
     */
    public static void displayImage(Activity activity, String path, ImageView imageView) {

        Glide.with(activity)                             //配置上下文
                .load(Uri.parse(path))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.mipmap.ic_launcher)           //设置错误图片
                .into(imageView);
    }

    /**
     *
     * @param activity
     * @param path
     * @param simpleTarget
     */
    public static void displayImage(Activity activity, String path,SimpleTarget simpleTarget) {
        Glide.with(activity).load(path).asBitmap().into(simpleTarget);
    }


    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
