package com.pgkk.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by tanxueze on 2016/4/7.
 * <p/>
 * 设备参数
 */
public class DeviceInfo {
    private static final String TAG = "DeviceInfoUtils";


    /**
     * 检查手机网络(4G/3G/2G)是否连接
     */
    public static boolean isMobileNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 获取Android版本
     *
     * @return
     */
    public static String getAndroidID(Context context) {
        try {
            String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
            return androidId;
        } catch (Exception e) {
            Log.i(TAG, "check permissions is add");
        }
        return "0";
    }

    /**
     * 获取联网方式
     *
     * @param ctx
     * @return
     */
    public static String getNetworkInfo(Context ctx) {
        ConnectivityManager connectionManager =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        String info = null;
        if (networkInfo != null) {
            info = networkInfo.getExtraInfo();
        } else {
            return null;
        }
        return info;
    }

    /**
     * @param context
     * @return 没有网：0 WiFi：1 2G：2 3G：3 其他：4
     */
    public final static String getNetWorkType(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();
        if (netWrokInfo == null || !netWrokInfo.isAvailable()) {
            return "not";
        } else {
            String typeName = netWrokInfo.getTypeName().toUpperCase();
            if (typeName.contains("WIFI")) {
                return "wifi";
            } else if (typeName.contains("MOBILE")) {
                String extraInfo = netWrokInfo.getExtraInfo();
                if (extraInfo != null && extraInfo.toUpperCase().contains("3G")) {
                    return "3g";
                } else if (extraInfo != null && extraInfo.toUpperCase().contains("4G")) {
                    return "4g";
                } else {
                    return "2g";
                }
            } else {
                return "unknown";
            }
        }
    }

    @SuppressWarnings("deprecation")
    public static Point getScreenInfo(Context context) {
        final Point point = new Point();
        if (context == null) {
            return point;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) { // Older device
            try {
                point.x = display.getWidth();
                point.y = display.getHeight();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e(TAG, e.getMessage(), e);
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.e(TAG, e.getMessage(), e);
        }
        return point;
    }

    /**
     * 判断手机是否是飞行模式
     *
     * @param context
     * @return
     */
    public static boolean isAirplaneMode(Context context) {
        try {
            int isAirplaneMode =
                    Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);
            return (isAirplaneMode == 1) ? true : false;

        } catch (Exception e) {
            // TODO: handle exception
            Log.i(TAG, "check permissions is add");
            return false;
        }
    }


    /**
     * 获取ICCID
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getICCID(Context context) {
        String iccid = null;
        try {
            TelephonyManager telephonyManager = null;
            telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
            iccid = telephonyManager.getSimSerialNumber();
            if (TextUtils.isEmpty(iccid)) {
                Class<? extends TelephonyManager> tmClass = telephonyManager.getClass();
                Class<?>[] parameter = new Class[1];
                parameter[0] = int.class;
                Method getImsiMethod = tmClass.getMethod("getSimSerialNumberGemini", int.class);

                if (null != getImsiMethod) {
                    // 先取SIM1
                    iccid = (String) getImsiMethod.invoke(telephonyManager, 1);
                    if (TextUtils.isEmpty(iccid)) {
                        iccid = (String) getImsiMethod.invoke(telephonyManager, 0);
                    }
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "check permissions is add");
        } catch (Error e) {
            Log.i(TAG, "check permissions is add");
        }
        return TextUtils.isEmpty(iccid) == true ? "0" : iccid;
    }

    /**
     * 获取Android系统版本
     *
     * @return
     */
    public static int getVresion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 主板
     *
     * @return
     */
    public static String getBoarl() {
        return android.os.Build.BOARD;
    }

    public static String getMANUFACTURER() {
        try {

            return android.os.Build.MANUFACTURER;
        } catch (Exception e) {
            // TODO: handle exception
            return e.getMessage();
        }
    }


    /**
     * 先获取手机IMSI
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getIMSI(Context context) {
        String imsi = null;
        try {
            TelephonyManager mTelephonyManager = null;
            mTelephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
            imsi = mTelephonyManager.getSubscriberId();
            if (TextUtils.isEmpty(imsi)) {
                Class<? extends TelephonyManager> tmClass = mTelephonyManager.getClass();
                Method getImsiMethod = tmClass.getMethod("getSubscriberIdGemini", Integer.TYPE);
                if (null != getImsiMethod) {
                    // 先取SIM2
                    imsi = (String) getImsiMethod.invoke(mTelephonyManager, 1);
                    if (TextUtils.isEmpty(imsi)) {
                        imsi = (String) getImsiMethod.invoke(mTelephonyManager, 0);
                    }
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "check permissions is add");
            return "0";
        }
        return imsi;
    }

    /**
     * 判断是否为大陆卡
     *
     * @param context
     * @return
     */
    public static boolean isMainland(Context context) {
        String imsi = getIMSI(context);
        if (!TextUtils.isEmpty(imsi) && imsi.startsWith("460")) {
            return true;
        }
        return false;
    }

    /**
     * 获取安装路径
     *
     * @param context
     * @return
     */
    public static String getAppPath(Context context) {
        return context.getPackageResourcePath();
    }

    /**
     * 是否为模拟器
     *
     * @param context
     * @return
     */
    public static boolean isEmulator(Context context) {
        try {
            @SuppressLint("WrongConstant") TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            @SuppressLint("MissingPermission") String imei = tm.getDeviceId();
            if (imei == null || imei.equals("000000000000000")) {
                return true;
            }
        } catch (Exception e) {
            Log.i(TAG, "check permissions is add");
        }
        return false;
    }

    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getImei(Context context) {
        try {
            TelephonyManager telephonyManager =
                    (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();
        } catch (Exception e) {
            // TODO: handle exception
            Log.i(TAG, "check permissions is add");
            return "0";
        }
    }

    /**
     * 通过wifiManager获取mac地址
     *
     * @return Mac Address
     * @attention Wifi
     */
    @SuppressLint("MissingPermission")
    public static String getMacFromWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String mResult = wifiInfo.getMacAddress();
        return mResult;
    }

    /**
     * @param context
     * @return 判断当前是否使用wifi连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") boolean result = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ? true : false;
        return result;
    }


    /**
     * 获得内存大小
     *
     * @return
     */
    public static String getTotalMemory() {
        FileReader fr = null;
        BufferedReader br = null;
        String text;
        try {
            fr = new FileReader("/proc/meminfo");
            br = new BufferedReader(fr, 8);
            text = br.readLine();
            String[] array = text.split("\\s+");
            //转换为GB显示
            float memory = Float.valueOf(array[1]) / 1024 / 1024;
            //设置两位有效数字
            DecimalFormat decimalFormat = new DecimalFormat("######0.00");
            String p = decimalFormat.format(memory);
            return p;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }

        }
    }


    /**
     * 获得存储大小
     *
     * @return
     */
    public static String getAllAvaliableSize() {
        float allSize = getSdSize() + getRomTotalSize();
        float memory = Float.valueOf(allSize) / 1024 / 1024 / 1024;
        DecimalFormat decimalFormat = new DecimalFormat("######0.00");
        String p = decimalFormat.format(memory);
        return p;
    }

    /**
     * SD卡存储
     * 兼容低版本
     *
     * @return
     */
    public static long getSdSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return blockSize * availableBlocks;
    }

    /**
     * 机身存储
     * 兼容低版本
     *
     * @return
     */

    public static long getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    /**
     * cpu信息
     *
     * @return
     */
    public static String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        Log.i(TAG, "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
        return cpuInfo;
    }


    /**
     * 当前语言版本
     *
     * @param context
     * @return
     */
    public static String getLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getLanguage();
    }


    /**
     * 显示频
     *
     * @param context
     * @return
     */
    public static String getDisplayMetrics(Context context) {
        String str = "";
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float density = dm.density;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        str += "The absolute width: " + String.valueOf(screenWidth) + "pixels/n";
        str += "The absolute heightin: " + String.valueOf(screenHeight) + "pixels/n";
        str += "The logical density of the display. : " + String.valueOf(density) + "/n";
        str += "X dimension : " + String.valueOf(xdpi) + "pixels per inch/n";
        str += "Y dimension : " + String.valueOf(ydpi) + "pixels per inch/n";
        return str;
    }


    /**
     * android系统定制商
     *
     * @return
     */
    public static String getBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * cpu指令集
     *
     * @return
     */
    public static String getCpu_Abi() {
        return android.os.Build.CPU_ABI;
    }

    /**
     * 设备参数
     *
     * @return
     */
    public static String getDevice() {
        return android.os.Build.DEVICE;
    }

    /**
     * 显示屏参数
     *
     * @return
     */
    public static String getDisplay() {
        return android.os.Build.DISPLAY;
    }

    /**
     * 硬件名称
     *
     * @return
     */
    public static String getFingerprint() {
        return android.os.Build.FINGERPRINT;
    }

    /**
     * 修订版本列表
     *
     * @return
     */
    public static String getBuild_id() {
        return android.os.Build.ID;
    }

    /**
     * 硬件制造商
     *
     * @return
     */
    public static String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 手机制造商
     *
     * @return
     */
    public static String getProduct() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 描述build的标签
     *
     * @return
     */
    public static String getBuild_tags() {
        return android.os.Build.TAGS;
    }

    /**
     * builder类型
     *
     * @return
     */
    public static String getBuild_type() {
        return android.os.Build.TYPE;
    }


    /**
     * 获取sim卡电话号码
     */
    public static String getSimPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String phone = tm.getLine1Number();
        if (phone != null && phone.contains("+86")) {
            phone = phone.substring(3);
        } else if (phone == null) {
            phone = "null";
        }
        return phone;
    }

    /**
     * 地理位置
     *
     * @param context
     */
    @SuppressLint("MissingPermission")
    public static String getLocation(Context context) {
        final double[] mLongitude = new double[1];//纬度
        final double[] mLatitude = new double[1];//纬度
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                    @Override
                    public void onStatusChanged(String provider, int status,
                                                Bundle extras) {
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        Log.i(TAG, "enable");
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Log.i(TAG, "disable");
                    }

                    @Override
                    public void onLocationChanged(Location location) {
                        mLatitude[0] = location.getLatitude();
                        mLongitude[0] = location.getLongitude();
                        Log.e(TAG, mLatitude[0] + ":" + mLongitude[0]);
                    }
                });
        return mLatitude[0] + ":" + mLongitude[0];
    }

    /**
     * 手机密度
     * @return
     */
    public static int getDpi(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }



    /**
     * 获取设备ID
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

}
