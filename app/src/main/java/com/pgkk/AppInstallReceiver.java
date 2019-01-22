package com.pgkk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by tanxueze on 2018/5/8.
 */

public class AppInstallReceiver extends BroadcastReceiver {


    /**
     * 注册通知
     * @param context
     * @param intent
     */
//    <receiver android:name=".AppInstallReceiver"
//    android:label="@string/app_name">
//            <intent-filter>
//                <action android:name="android.intent.action.PACKAGE_ADDED" />
//                <action android:name="android.intent.action.PACKAGE_REPLACED" />
//                <action android:name="android.intent.action.PACKAGE_REMOVED" />
//                <data android:scheme="package" />
//            </intent-filter>
//        </receiver>


    @Override
    public void onReceive(Context context, Intent intent) {
        PackageManager manager = context.getPackageManager();
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Toast.makeText(context, "安装成功" + packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Toast.makeText(context, "卸载成功" + packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Toast.makeText(context, "替换成功" + packageName, Toast.LENGTH_LONG).show();
        }
    }


    /**
     * 安装apk
     */
//    private void install(String filePath) {
//        Log.i(TAG, "开始执行安装: " + filePath);
//        File apkFile = new File(filePath);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Log.w(TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            Uri contentUri = FileProvider.getUriForFile(
//                    mContext
//                    , "你的包名.fileprovider"
//                    , apkFile);
//            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
//        } else {
//            Log.w(TAG, "正常进行安装");
//            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
//        }
//        startActivity(intent);
//    }
}

