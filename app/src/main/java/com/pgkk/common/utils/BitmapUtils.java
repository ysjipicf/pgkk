package com.pgkk.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 */

public class BitmapUtils {

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    private static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）
     *
     * @param srcPath
     * @return
     */
    public static Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 将压缩的bitmap保存到SDCard卡临时文件夹，用于上传
     *
     * @param filename
     * @param bit
     * @return
     */
    private static String saveMyBitmap(String filename, Bitmap bit) {
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/chdd/";
        String filePath = baseDir + filename;
        File dir = new File(baseDir);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File f = new File(filePath);
        try {
            f.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            bit.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return filePath;
    }

    /**
     * 压缩上传路径
     *
     * @param path
     * @return
     */
    public static String compressImageUpload(String path) {
        String filename = path.substring(path.lastIndexOf("/") + 1);
        Bitmap image = getImage(path);
        return saveMyBitmap(filename, image);
    }


    /**
     * 清除缓存文件
     */
    public static void deleteCacheFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/chdd/");
        RecursionDeleteFile(file);
    }

    /**
     * 递归删除
     */
    private static void RecursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 从指定文件当中获取指定比例的图片
     *
     * @param path
     * @param scale
     * @return
     */
    public static Bitmap getBitmap(String path, int scale) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = scale;
        return BitmapFactory.decodeFile(path, opts);
    }

    /**
     * 从指定文件当中获取图片
     *
     * @param path
     * @return
     */
    public static Bitmap getBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 从指定文件当中获取指定宽高的图片
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitmap(String path, int width, int height) {
        Bitmap bm = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        bm = BitmapFactory.decodeFile(path);
        int x = opts.outWidth / width;
        int y = opts.outHeight / height;
        opts.inSampleSize = x > y ? x : y;
        opts.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path);
        return bm;
    }

    /**
     * 字节数组按比例返回图片
     *
     * @param data
     * @param scale
     * @return
     */
    public static Bitmap getBitmap(byte[] data, int scale) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = scale;
        return BitmapFactory.decodeByteArray(data, 0, data.length, opts);
    }

    /**
     * 字节数组返回原图片
     *
     * @param data
     * @return
     */
    public static Bitmap getBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     * 字节数组按长和宽返回图片
     *
     * @param data
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitmap(byte[] data, int width, int height) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        int x = opts.outWidth / width;
        int y = opts.outHeight / height;
        int scale = x > y ? x : y;
        return getBitmap(data, scale);
    }

    /**
     * 指定流得到图片
     *
     * @param in
     * @return
     */
    public static Bitmap getBitmap(InputStream in) {
        return BitmapFactory.decodeStream(in);
    }

    /**
     * 保存图片到指定位置
     *
     * @param bm
     * @param path
     */
    public static void save(Bitmap bm, String path) {
        OutputStream out = null;

        try {
            File file = new File(path);
            if (file.getParentFile() != null) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) file.createNewFile();

                out = new BufferedOutputStream(new FileOutputStream(file));
                bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 得到圆角图片
     *
     * @param bitmap
     * @param roundPx
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 返回drawable
     *
     * @param context
     * @param bitmap
     * @return
     */
    public static BitmapDrawable getDrawable(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * 返回SD卡根目录
     */
    public static String getSDRootPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
            return sdDir.toString();
        }
        return null;
    }

    /**
     * 将Bitmap存入文件
     *
     * @Title: saveDrawableToCache
     * @param bitmap
     * @param filePath
     * @return void
     * @date 2012-12-14 上午9:27:38
     */
    public static void saveDrawableToCache(Bitmap bitmap, String filePath) {
        OutputStream outStream = null;
        try {
            File file = new File(filePath);
            file.createNewFile();
            outStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Bitmap → byte[]
     *
     * @param bm
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        if (!bm.isRecycled()) {
            bm.isRecycled();
            System.gc();
        }
        return b;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap getBitmapFromPath(String image_path, int screenWidth, int screenHeight) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;// 不会真真的去解析图片,只是获取图片的头部信息，宽高等
        BitmapFactory.decodeFile(image_path, opts);
        int scale = BitmapUtils.calculateInSampleSize(opts, screenWidth, screenHeight);
        // 真的解析图片
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(image_path, opts);
        return bitmap;
    }

    /**
     * drawable转bitmap
     * @param drawable
     * @return
     */
    public static Bitmap Drawable2Bitmap(Drawable drawable){
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bm = bd.getBitmap();
        return bm;
    }
}
