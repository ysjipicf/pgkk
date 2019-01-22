package com.pgkk.common.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by tanxueze on 2017/12/12.
 */

public class FileUtils {
    /**
     * 将流保存为文件
     * @param is
     * @param savePath
     * @return
     * @throws IOException
     */

    public static final boolean saveInputStreamfromFile(InputStream is, File savePath) throws IOException {
        if (is != null && savePath != null) {
            // 如果目录不存在，则创建该目录
            if (!savePath.getParentFile().exists()) {
                savePath.getParentFile().mkdirs();
            }
            // 创建输入输出流
            BufferedInputStream bis = new BufferedInputStream(is);
            FileOutputStream out = new FileOutputStream(savePath);
            BufferedOutputStream bos = new BufferedOutputStream(out);

            int len = -1;
            byte[] bytes = new byte[1024];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
                bos.flush();
            }
            bos.close();
            out.close();
            bis.close();
            is.close();
            return true;
        }
        return false;
    }

    /**
     * 删除单个文件
     *
     * @param file
     */
    public static void deleFile(File file) {
        if (file.isFile()) {
            file.delete();
        }
    }

    /**
     * 数组转换为文件
     *
     * @param b
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            if (!file.exists()) file.createNewFile();
            stream = new BufferedOutputStream(new FileOutputStream(file));
            stream.write(b);
            stream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 判断文件大小
     *
     * @param f 文件对象
     * @return
     */
    public static long getSize(File f) {
        FileInputStream fis = null;
        long s = 0;
        try {
            fis = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (fis != null) {
                s = fis.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;

    }

    /**
     * 文件复制粘贴
     *
     * @param source 来源
     * @param dest 目地
     */
    public void fileCopy(File source, File dest) {
        FileInputStream sourceFile = null;
        FileOutputStream destinationFile = null;
        try {
            dest.createNewFile();
            sourceFile = new FileInputStream(source);
            destinationFile = new FileOutputStream(dest);
            int nbLecture = 0;
            while ((nbLecture = sourceFile.read()) != -1) {
                destinationFile.write(nbLecture);
                destinationFile.flush();
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sourceFile != null) sourceFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (destinationFile != null) destinationFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建目录
     *
     * @param file
     */
    public static void createDirectory(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 读取输入流，返回字节数组
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(InputStream is) throws IOException {
        if (is != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            readData(is, out);
            return out.toByteArray();
        }
        return null;
    }

    /**
     * 将输入流中的数据保存到 指定的文件中
     *
     * @param is
     * @param path
     * @throws IOException
     */
    public static void save(InputStream is, String path) throws IOException {
        if (is != null && path != null) {
            FileOutputStream out = new FileOutputStream(path);
            readData(is, out);
        }
    }

    /**
     * 将输入流中的数据保存到 指定的文件中
     *
     * @param is
     * @param path
     * @throws IOException
     */
    public static void save(InputStream is, File path) throws IOException {
        if (is != null && path != null) {
            FileOutputStream out = new FileOutputStream(path);
            readData(is, out);
        }
    }

    /**
     * 从输入流 向 输出流写出数据
     *
     * @param is
     * @param out
     * @throws IOException
     */
    public static void readData(InputStream is, OutputStream out) throws IOException {
        if (is != null && out != null) {
            BufferedInputStream in = new BufferedInputStream(is);
            int len = -1;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.close();
            in.close();
            is.close();
        }
    }

    /**
     * 字符转码
     *
     * @param str_filepath
     * @return
     */
    public String convertCodeAndGetText(String str_filepath) {// 转码
        File file = new File(str_filepath);
        BufferedReader reader;
        String text = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(fis);
            in.mark(4);
            byte[] first3bytes = new byte[3];
            in.read(first3bytes);// 找到文档的前三个字节并自动判断文档类型。
            in.reset();
            if (first3bytes[0] == (byte) 0xEF && first3bytes[1] == (byte) 0xBB && first3bytes[2] == (byte) 0xBF) {// utf-8
                reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            } else if (first3bytes[0] == (byte) 0xFF && first3bytes[1] == (byte) 0xFE) {
                reader = new BufferedReader(new InputStreamReader(in, "unicode"));
            } else if (first3bytes[0] == (byte) 0xFE && first3bytes[1] == (byte) 0xFF) {
                reader = new BufferedReader(new InputStreamReader(in, "utf-16be"));
            } else if (first3bytes[0] == (byte) 0xFF && first3bytes[1] == (byte) 0xFF) {
                reader = new BufferedReader(new InputStreamReader(in, "utf-16le"));
            } else {
                reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            }
            String str = reader.readLine();
            while (str != null) {
                text = text + str + "/n";
                str = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
